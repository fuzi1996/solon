package org.noear.solon.cloud.extend.water.service;

import org.noear.snack.ONode;
import org.noear.solon.Solon;
import org.noear.solon.Utils;
import org.noear.solon.cloud.CloudDiscoveryHandler;
import org.noear.solon.cloud.CloudProps;
import org.noear.solon.cloud.model.Discovery;
import org.noear.solon.cloud.model.Instance;
import org.noear.solon.cloud.service.CloudDiscoveryObserverEntity;
import org.noear.solon.cloud.service.CloudDiscoveryService;
import org.noear.solon.cloud.utils.IntervalUtils;
import org.noear.solon.core.Signal;
import org.noear.solon.health.HealthHandler;
import org.noear.water.WaterClient;
import org.noear.water.model.DiscoverM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

/**
 * 注册与发现服务
 *
 * @author noear
 * @since 1.2
 */
public class CloudDiscoveryServiceWaterImpl extends TimerTask implements CloudDiscoveryService {
    static final Logger log = LoggerFactory.getLogger(CloudDiscoveryServiceWaterImpl.class);

    //String checkPathDefault;
    private String alarmMobile;
    private long refreshInterval;
    private boolean unstable;

    public CloudDiscoveryServiceWaterImpl(CloudProps cloudProps) {
        unstable = Solon.cfg().isFilesMode()
                || Solon.cfg().isDriftMode();
        //checkPathDefault = WaterProps.instance.getDiscoveryHealthCheckPath();
        alarmMobile =cloudProps.getAlarm();
        refreshInterval = IntervalUtils.getInterval(cloudProps.getDiscoveryRefreshInterval("5s"));
    }

    /**
     * 健康检测刷新间隔时间（仅当isFilesMode时有效）
     */
    public long getRefreshInterval() {
        return refreshInterval;
    }

    @Override
    public void run() {
        //外面5s跑一次
        try {
            //主动刷新健康
            if (Solon.cfg().isFilesMode()) {
                runByFile();
            }

        } catch (Throwable e) {
            log.warn(e.getMessage(), e);
        }
    }

    /**
     * 文件模式运行时
     * */
    private void runByFile() {
        if (Utils.isNotEmpty(Solon.cfg().appName())) {
            try {
                for (Signal signal : Solon.app().signals()) {
                    Instance instance = Instance.localNew(signal);
                    register(Solon.cfg().appGroup(), instance);
                }
            } catch (Throwable ex) {

            }

            try {
                observerMap.forEach((k, v) -> {
                    onUpdate(v.group, v.service);
                });
            } catch (Throwable ex) {

            }
        }
    }


    @Override
    public void register(String group, Instance instance) {
        String meta = null;
        if (instance.meta() != null && instance.meta().size() > 0) {
            meta = ONode.stringify(instance.meta());
        }

        String protocol = Utils.annoAlias(instance.protocol(),"http");
        String code_location = Solon.cfg().sourceLocation().getPath();
        String checkPath;
        if (protocol.startsWith("http")) {
            checkPath = HealthHandler.HANDLER_PATH;
        } else {
            checkPath = instance.uri();
        }

        if (Solon.cfg().isFilesMode()) {
            //自己主动刷新
            WaterClient.Registry.register(Solon.cfg().appGroup(), instance.service(), instance.address(), meta, checkPath, 1, alarmMobile, code_location, unstable);
        } else {
            //被动接收检测
            WaterClient.Registry.register(Solon.cfg().appGroup(), instance.service(), instance.address(), meta, checkPath, 0, alarmMobile, code_location, unstable);
        }
    }

    @Override
    public void registerState(String group, Instance instance, boolean health) {
        String meta = null;
        if (instance.meta() != null) {
            meta = ONode.stringify(instance.meta());
        }

        WaterClient.Registry.set(group, instance.service(), instance.address(), meta, health);
    }

    @Override
    public void deregister(String group, Instance instance) {
        String meta = null;
        if (instance.meta() != null) {
            meta = ONode.stringify(instance.meta());
        }

        WaterClient.Registry.unregister(group, instance.service(), instance.address(), meta);
    }

    @Override
    public Discovery find(String group, String service) {
        Instance instance = Instance.local();

        DiscoverM d1 = WaterClient.Registry.discover(group, service, instance.service(), instance.address());
        return ConvertUtil.from(service, d1);
    }

    Map<String, String> serviceMap = new HashMap<>();
    Map<CloudDiscoveryHandler, CloudDiscoveryObserverEntity> observerMap = new HashMap<>();

    @Override
    public void attention(String group, String service, CloudDiscoveryHandler observer) {
        observerMap.put(observer, new CloudDiscoveryObserverEntity(group, service, observer));
        serviceMap.put(service, service);
    }

    public void onUpdate(String group, String service) {
        if (serviceMap.containsKey(service)) {
            Discovery discovery = find(group, service);

            observerMap.forEach((k, v) -> {
                if (service.equals(v.service)) {
                    v.handle(discovery);
                }
            });
        }
    }
}
