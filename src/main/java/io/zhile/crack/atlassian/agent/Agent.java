package io.zhile.crack.atlassian.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author pengzhile
 * @link https://zhile.io
 * @version 1.0
 */
public class Agent {
    public static void premain(String args, Instrumentation inst) {

        System.out.println("====================================================");
        System.out.println("=======        Atlassian Crack Agent         =======");
        System.out.println("====================================================");

        //noinspection UnnecessaryLocalVariable
        final String appLibPath = args;
        System.out.printf("application lib: %s \n", appLibPath);
        try {
            inst.addTransformer(new KeyTransformer(appLibPath));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
