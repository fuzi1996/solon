package org.noear.solon.test;

import org.junit.jupiter.api.extension.*;
import org.noear.solon.Utils;

/**
 * @author noear
 * @since 1.10
 */
public class SolonJUnit5Extension implements TestInstanceFactory {
    @Override
    public Object createTestInstance(TestInstanceFactoryContext factory, ExtensionContext extensionContext) throws TestInstantiationException {

        try {
            //init
            initDo(factory.getTestClass());

            //create
            Object tmp = Utils.newInstance(factory.getTestClass());
            RunnerUtils.initTestTarget(tmp);

            return tmp;
        } catch (Throwable e) {
            throw new TestInstantiationException("Test class instantiation failed: " + factory.getTestClass().getName());
        }
    }

    private Class<?> klassCached;

    private void initDo(Class<?> klass) throws Throwable{
        if (klassCached == null) {
            klassCached = klass;
        } else {
            return;
        }

        RunnerUtils.initRunner(klass);
    }
}
