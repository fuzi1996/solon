package org.noear.solon.boot.jetty;

import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.noear.solon.Solon;
import org.noear.solon.Utils;
import org.noear.solon.boot.ServerConstants;
import org.noear.solon.boot.ServerLifecycle;
import org.noear.solon.boot.ServerProps;
import org.noear.solon.boot.jetty.http.JtContainerInitializer;
import org.noear.solon.boot.jetty.http.JtHttpContextHandler;
import org.noear.solon.boot.jetty.http.JtHttpContextServletHandler;
import org.noear.solon.boot.prop.ServerSslProps;
import org.noear.solon.boot.prop.impl.HttpServerProps;
import org.noear.solon.boot.http.HttpServerConfigure;
import org.noear.solon.core.runtime.NativeDetector;
import org.noear.solon.core.util.ResourceUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Executor;

abstract class JettyServerBase implements ServerLifecycle , HttpServerConfigure {
    protected Executor executor;
    protected HttpServerProps props = new HttpServerProps();
    protected boolean enableSsl = true;
    private boolean isSecure;

    public boolean isSecure() {
        return isSecure;
    }

    protected Set<Integer> addHttpPorts = new LinkedHashSet<>();

    private ServerSslProps sslProps;

    protected boolean supportSsl() {
        if (sslProps == null) {
            sslProps = ServerSslProps.of(ServerConstants.SIGNAL_HTTP);
        }

        return sslProps.isEnable() && sslProps.getSslKeyStore() != null;
    }

    /**
     * 是否允许Ssl
     */
    @Override
    public void enableSsl(boolean enable) {
        this.enableSsl = enable;
    }

    /**
     * 添加 HttpPort（当 ssl 时，可再开个 http 端口）
     */
    @Override
    public void addHttpPort(int port) {
        addHttpPorts.add(port);
    }

    public HttpServerProps getProps() {
        return props;
    }

    @Override
    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    /**
     * 创建连接器（支持https）
     *
     * @since 1.6
     */
    protected ServerConnector getConnector(Server server, String host, int port, boolean autoSsl) throws RuntimeException {
        //配置 //http://www.eclipse.org/jetty/documentation/jetty-9/index.html
        HttpConfiguration config = new HttpConfiguration();
        if (ServerProps.request_maxHeaderSize > 0) {
            config.setRequestHeaderSize(ServerProps.request_maxHeaderSize);
        }

        HttpConnectionFactory httpFactory = new HttpConnectionFactory(config);
        ServerConnector serverConnector;

        if (enableSsl && autoSsl && supportSsl()) {


            String sslKeyStore = sslProps.getSslKeyStore();
            String sslKeyStoreType = sslProps.getSslKeyType();
            String sslKeyStorePassword = sslProps.getSslKeyPassword();

            SslContextFactory.Server contextFactory = new SslContextFactory.Server();

            if (Utils.isNotEmpty(sslKeyStore)) {
                URL url = ResourceUtil.findResource(sslKeyStore);
                if (url != null) {
                    sslKeyStore = url.toString();
                }

                contextFactory.setKeyStorePath(sslKeyStore);
            }

            if (Utils.isNotEmpty(sslKeyStoreType)) {
                contextFactory.setKeyStoreType(sslKeyStoreType);
            }

            if (Utils.isNotEmpty(sslKeyStorePassword)) {
                contextFactory.setKeyStorePassword(sslKeyStorePassword);
            }

            SslConnectionFactory sslFactory = new SslConnectionFactory(contextFactory, HttpVersion.HTTP_1_1.asString());

            serverConnector = new ServerConnector(server, executor, null, null, -1, -1, sslFactory, httpFactory);
            //this(server, (Executor)null, (Scheduler)null, (ByteBufferPool)null, -1, -1, factories);
            isSecure = true;
        } else {
            serverConnector = new ServerConnector(server, executor, null, null, -1, -1, httpFactory);
            //this(server, (Executor)null, (Scheduler)null, (ByteBufferPool)null, -1, -1, factories);
        }


        serverConnector.setPort(port);

        if (Utils.isNotEmpty(host)) {
            serverConnector.setHost(host);
        }

        return serverConnector;
    }

    protected ServletContextHandler getServletHandler() throws IOException {
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/");
        handler.addServlet(JtHttpContextServletHandler.class, "/").setAsyncSupported(true);
        handler.setBaseResource(new ResourceCollection(getResourceURLs()));


        //添加session state 支持
        if (Solon.app().enableSessionState()) {
            handler.setSessionHandler(new SessionHandler());

            if (ServerProps.session_timeout > 0) {
                handler.getSessionHandler().setMaxInactiveInterval(ServerProps.session_timeout);
            }
        }

        //添加容器初始器
        handler.addLifeCycleListener(new JtContainerInitializer(handler.getServletContext()));

        return handler;
    }

    protected Handler getJettyHandler() {
        //::走Handler接口
        JtHttpContextHandler _handler = new JtHttpContextHandler();

        if (Solon.app().enableSessionState()) {
            //需要session state
            //
            SessionHandler s_handler = new SessionHandler();

            if (ServerProps.session_timeout > 0) {
                s_handler.setMaxInactiveInterval(ServerProps.session_timeout);
            }

            s_handler.setHandler(_handler);

            return s_handler;
        } else {
            //不需要session state
            //
            return _handler;
        }
    }


    protected String[] getResourceURLs() throws FileNotFoundException {
        URL rootURL = getRootPath();
        if (rootURL == null) {
            if (NativeDetector.inNativeImage()) {
                return new String[]{};
            }

            throw new FileNotFoundException("Unable to find root");
        }

        String resURL = rootURL.toString();

        if (Solon.cfg().isDebugMode() && (resURL.startsWith("jar:") == false)) {
            int endIndex = resURL.indexOf("target");
            String debugResURL = resURL.substring(0, endIndex) + "src/main/resources/";
            return new String[]{debugResURL, resURL};
        }

        return new String[]{resURL};
    }

    protected URL getRootPath() {
        URL root = ResourceUtil.getResource("/");
        if (root != null) {
            return root;
        }
        try {
            URL temp = ResourceUtil.getResource("");
            if (temp == null) {
                return null;
            }

            String path = temp.toString();
            if (path.startsWith("jar:")) {
                int endIndex = path.indexOf("!");
                path = path.substring(0, endIndex + 1) + "/";
            } else {
                return null;
            }
            return new URL(path);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
