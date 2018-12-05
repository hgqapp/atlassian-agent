package io.zhile.crack.atlassian.utils;

import javax.xml.bind.DatatypeConverter;

/**
 * @author pengzhile
 * @link https://zhile.io
 * @version 1.0
 */
public class Base64 {
    public static byte[] decode(String val) {
        return DatatypeConverter.parseBase64Binary(val);
    }

    public static String encode(byte[] data) {
        return DatatypeConverter.printBase64Binary(data);
    }
}
