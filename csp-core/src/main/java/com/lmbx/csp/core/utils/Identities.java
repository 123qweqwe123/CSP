package com.lmbx.csp.core.utils;

import java.util.UUID;

/**
 * Description:
 * <pre>
 * </pre>
 *
 * @author javahuang
 */
public class Identities {

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
