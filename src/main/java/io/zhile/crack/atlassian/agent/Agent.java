package io.zhile.crack.atlassian.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author pengzhile
 * @link https://zhile.io
 * @version 1.0
 */
public class Agent {
    public static void premain(String args, Instrumentation inst) {
        try {
            inst.addTransformer(new KeyTransformer());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
