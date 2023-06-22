package org.noear.solon.extend.graphql;

import org.noear.solon.core.AopContext;
import org.noear.solon.core.Plugin;
import org.noear.solon.extend.graphql.annotation.QueryMapping;
import org.noear.solon.extend.graphql.config.GraphqlConfiguration;
import org.noear.solon.extend.graphql.controller.GraphqlController;
import org.noear.solon.extend.graphql.properties.GraphqlProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fuzi1996
 * @since 2.3
 */
public class GraphqlPlugin implements Plugin {

    private static Logger log = LoggerFactory.getLogger(GraphqlPlugin.class);

    @Override
    public void start(AopContext context) {
        log.info("load GraphqlPlugin ...");
        context.beanExtractorAdd(QueryMapping.class, (bw, method, anno) -> {
            log.info("1");
            System.out.println(bw.toString());
            System.out.println(method.toString());
            System.out.println(anno.toString());

            anno.name();

        });

        context.lifecycle(-99, () -> {
            context.beanMake(GraphqlProperties.class);
            context.beanMake(GraphqlConfiguration.class);
            context.beanMake(GraphqlController.class);
        });
    }
}
