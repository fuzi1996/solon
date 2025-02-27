package org.noear.solon.socketd;

import org.noear.solon.Solon;
import org.noear.solon.core.event.EventBus;
import org.noear.solon.core.handle.MethodType;
import org.noear.solon.core.message.Listener;
import org.noear.solon.core.message.Message;
import org.noear.solon.core.message.MessageFlag;
import org.noear.solon.core.message.Session;
import org.noear.solon.core.util.RunUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * 路由器监听者
 *
 * @author noear
 * @since 1.6
 */
public class RouterListener implements Listener{
    static final Logger log = LoggerFactory.getLogger(RouterListener.class);


    //
    // Listener 实现
    //
    /**
     * 打开会话时
     * */
    @Override
    public void onOpen(Session session) {
        RunUtil.parallel(() -> {
            onOpen0(session);
        });
    }

    private void onOpen0(Session session) {
        try {
            //路由监听模式（起到过滤器作用）
            Listener sl = get(session);
            if (sl != null) {
                sl.onOpen(session);
            } else {

                if(Solon.app().enableWebSocketMvc() == false) {
                    if (session.method() == MethodType.WEBSOCKET && session.listener() == null) {
                        session.close();
                        return;
                    }
                }

                if(Solon.app().enableSocketMvc() == false) {
                    if (session.method() == MethodType.SOCKET && session.listener() == null) {
                        session.close();
                        return;
                    }
                }
            }

            //todo: 实例监听者（SessionBase）
            //if (session.listener() != null) {
            //    session.listener().onOpen(session);
            //}
        } catch (Throwable e) {
            log.warn(e.getMessage(), e);
        }
    }

    /**
     * 收到消息时
     * */
    @Override
    public void onMessage(Session session, Message message) throws IOException {
        if (message == null) {
            return;
        }

        //
        //线程池处理，免得被卡住
        //
        RunUtil.parallel(() -> {
            onMessage0(session, message);
        });
    }

    private void onMessage0(Session session, Message message) {
        try {
            log.trace("Listener proxy receive: {}", message);

            //路由监听模式（起到过滤器作用）
            Listener sl = get(session);
            if (sl != null) {
                sl.onMessage(session, message);
            }

            //实例监听者
            if (session.listener() != null) {
                session.listener().onMessage(session, message);
            }

            //心跳包不进入处理流程
            if (message.flag() == MessageFlag.heartbeat) {
                return;
            }

            //如果是响应体，尝试直接通知Request
            if (message.flag() == MessageFlag.response) {
                //flag 消息标志（-1握手包；0发起包； 1响应包）
                //
                CompletableFuture<Message> request = RequestManager.get(message.key());

                //请求模式
                if (request != null) {
                    RequestManager.remove(message.key());
                    request.complete(message);
                    return;
                }
            }

            //代理模式
            if (message.getHandled() == false) {
                SocketContextHandler.instance.handle(session, message);
            }
        } catch (Throwable e) {
            if (onError0(session, e) == false) {
                log.warn(e.getMessage(), e);
            }
        }
    }

    /**
     * 关闭会话时
     * */
    @Override
    public void onClose(Session session) {
        RunUtil.parallel(() -> {
            onClose0(session);
        });
    }

    private void onClose0(Session session) {
        try {
            //路由监听模式
            Listener sl = get(session);
            if (sl != null) {
                sl.onClose(session);
            }

            //实例监听者
            if (session.listener() != null) {
                session.listener().onClose(session);
            }
        } catch (Throwable e) {
            log.warn(e.getMessage(), e);
        }
    }

    /**
     * 出错时
     * */
    @Override
    public void onError(Session session, Throwable error) {
        RunUtil.parallel(() -> {
            onError0(session, error);
        });
    }

    private boolean onError0(Session session, Throwable error) {
        try {
            boolean handled = false;
            //路由监听模式（起到过滤器作用）
            Listener sl = get(session);
            if (sl != null) {
                handled = true;
                sl.onError(session, error);
            }

            //实例监听者
            if (session.listener() != null) {
                handled = true;
                session.listener().onError(session, error);
            }

            return handled;
        } catch (Throwable e) {
            log.warn(e.getMessage(), e);
            return true;
        }
    }

    //获取监听器
    private Listener get(Session s) {
        //
        //路由监听模式，可实现双向RPC模式
        //

        return Solon.app().router().matchOne(s);
    }
}
