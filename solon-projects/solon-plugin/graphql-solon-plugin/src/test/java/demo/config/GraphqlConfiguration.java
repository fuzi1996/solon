package demo.config;

import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.TypeRuntimeWiring;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.extend.graphql.execution.RuntimeWiringConfigurer;

/**
 * @author fuzi1996
 * @since 2.3
 */
@Configuration
public class GraphqlConfiguration {

    @Bean
    public RuntimeWiringConfigurer staticConfigurer() {
        return (builder) -> builder.type(TypeRuntimeWiring.newTypeWiring("Query")
                .dataFetcher("hello", new StaticDataFetcher("world")));
    }
}
