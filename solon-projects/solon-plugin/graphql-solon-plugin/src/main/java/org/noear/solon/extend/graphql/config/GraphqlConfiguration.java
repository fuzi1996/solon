package org.noear.solon.extend.graphql.config;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.noear.solon.Solon;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.util.ResourceUtil;
import org.noear.solon.core.util.ScanUtil;
import org.noear.solon.extend.graphql.execution.DefaultGraphQlSource;
import org.noear.solon.extend.graphql.execution.DefaultSchemaResourceGraphQlSourceBuilder;
import org.noear.solon.extend.graphql.execution.GraphQlSource;
import org.noear.solon.extend.graphql.execution.RuntimeWiringConfigurer;
import org.noear.solon.extend.graphql.properties.GraphqlProperties;
import org.noear.solon.extend.graphql.properties.GraphqlProperties.Schema;
import org.noear.solon.extend.graphql.support.ClassPathResource;
import org.noear.solon.extend.graphql.support.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fuzi1996
 * @since 2.3
 */
@Configuration
public class GraphqlConfiguration {

    private static Logger log = LoggerFactory.getLogger(GraphqlConfiguration.class);

    public GraphqlConfiguration() {
    }

    @Bean
    public GraphQlSource registGraphqlSource(@Inject GraphqlProperties properties) {
        Schema schema = properties.getSchema();

        List<String> locations = schema.getLocations();
        List<String> fileExtensions = schema.getFileExtensions();

        Set<Resource> collect = locations.stream()
                .map(location -> ScanUtil.scan(location, (path) -> fileExtensions.stream().anyMatch(
                        path::endsWith))).flatMap(Set::stream).map(ResourceUtil::getResource)
                .map(ClassPathResource::new)
                .collect(Collectors.toSet());

        DefaultSchemaResourceGraphQlSourceBuilder defaultBuilder = new DefaultSchemaResourceGraphQlSourceBuilder();
        defaultBuilder.schemaResources(collect);

        Objects.requireNonNull(Solon.context())
                .getBeansOfType(RuntimeWiringConfigurer.class)
                .forEach(defaultBuilder::configureRuntimeWiring);

        GraphQLSchema graphQlSchema = defaultBuilder.getGraphQlSchema();
        GraphQL graphql = GraphQL.newGraphQL(graphQlSchema).build();

        log.info("GraphQlSource 生成");
        return new DefaultGraphQlSource(graphql, graphQlSchema);
    }
}
