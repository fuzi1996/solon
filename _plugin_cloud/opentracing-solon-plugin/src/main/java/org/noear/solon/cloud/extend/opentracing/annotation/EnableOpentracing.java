package org.noear.solon.cloud.extend.opentracing.annotation;

import java.lang.annotation.*;

/**
 * @author noear
 * @since 1.4
 */
@Deprecated
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableOpentracing {
}
