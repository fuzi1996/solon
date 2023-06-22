package org.noear.solon.extend.graphql.support;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author fuzi1996
 * @since 2.3
 */
public class ByteArrayResource implements Resource {

    private final byte[] byteArray;

    public ByteArrayResource(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.byteArray);
    }
}
