package demo.controller;

import org.noear.solon.annotation.Component;
import org.noear.solon.extend.graphql.annotation.QueryMapping;

/**
 * @author fuzi1996
 * @since 2.3
 */
@Component
public class BookController {

    @QueryMapping
    public Object bookById(String id) {
        return "bookById result";
    }
}
