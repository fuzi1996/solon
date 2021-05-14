package org.noear.solon.extend.data;

import org.noear.solon.SolonApp;
import org.noear.solon.core.*;
import org.noear.solon.core.cache.CacheService;
import org.noear.solon.extend.data.annotation.Cache;
import org.noear.solon.extend.data.annotation.CachePut;
import org.noear.solon.extend.data.annotation.CacheRemove;
import org.noear.solon.extend.data.annotation.Tran;
import org.noear.solon.extend.data.around.CacheInterceptor;
import org.noear.solon.extend.data.around.CachePutInterceptor;
import org.noear.solon.extend.data.around.CacheRemoveInterceptor;
import org.noear.solon.extend.data.around.TranInterceptor;

public class XPluginImp implements Plugin {
    @Override
    public void start(SolonApp app) {
        if (app.enableTransaction()) {
            Bridge.tranExecutorSet(TranExecutorImp.global);
        }

        if (app.enableCaching()) {
            CacheLib.cacheServiceAddIfAbsent("", CacheServiceDefault.instance);

            app.onEvent(BeanWrap.class, new CacheEventListener());
        }

        Aop.context().beanOnloaded(() -> {
            if (Aop.has(CacheService.class) == false) {
                Aop.wrapAndPut(CacheService.class, CacheServiceDefault.instance);
            }
        });

        Aop.context().beanInterceptorAdd(CachePut.class, 110, new CachePutInterceptor());
        Aop.context().beanInterceptorAdd(CacheRemove.class, 110, new CacheRemoveInterceptor());
        Aop.context().beanInterceptorAdd(Cache.class, 111, new CacheInterceptor());
        Aop.context().beanInterceptorAdd(Tran.class, 120, new TranInterceptor());
    }
}
