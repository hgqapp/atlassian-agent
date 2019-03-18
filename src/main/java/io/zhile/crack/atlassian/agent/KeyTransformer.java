package io.zhile.crack.atlassian.agent;

import javassist.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author pengzhile
 * @link https://zhile.io
 * @version 1.0
 */
public class KeyTransformer implements ClassFileTransformer {
    private static final String CN_KEY_SPEC = "java/security/spec/EncodedKeySpec";

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className == null) {
            return classfileBuffer;
        }

        if (className.equals(CN_KEY_SPEC)) {
            return handleKeySpec();
        }

        return classfileBuffer;
    }

    private byte[] handleKeySpec() throws IllegalClassFormatException {
        try {
            String b64f;
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get(CN_KEY_SPEC.replace('/', '.'));

            cp.importPackage("java.util.Arrays");
            try {
                Class.forName("java.util.Base64");
                cp.importPackage("java.util.Base64");
                b64f = "Base64.getDecoder().decode";
            } catch (ClassNotFoundException e) {
                try {
                    Class.forName("javax.xml.bind.DatatypeConverter");
                    cp.importPackage("javax.xml.bind.DatatypeConverter");
                    b64f = "DatatypeConverter.parseBase64Binary";
                } catch (ClassNotFoundException e1) {
                    throw new RuntimeException(e1);
                }
            }

            cc.addField(CtField.make("private static final byte[] __h_ok=" + b64f + "(\"MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYUAAoGBAIvfweZvmGo5otwawI3no7Udanxal3hX2haw962KL/nHQrnC4FG2PvUFf34OecSK1KtHDPQoSQ+DHrfdf6vKUJphw0Kn3gXm4LS8VK/LrY7on/wh2iUobS2XlhuIqEc5mLAUu9Hd+1qxsQkQ50d0lzKrnDqPsM0WA9htkdJJw2nS\");", cc));
            cc.addField(CtField.make("private static final byte[] __h_nk=" + b64f + "(\"MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYUAAoGBAO0DidNibJHhtgxAnM9NszURYU25CVLAlwFdOWhiUkjrjOY459ObRZDVd35hQmN/cCLkDox7y2InJE6PDWfbx9BsgPmPvH75yKgPs3B8pClQVkgIpJp08R59hoZabYuvm7mxCyDGTl2lbrOi0a3j4vM5OoCWKQjIEZ28OpjTyCr3\");", cc));
            CtConstructor cm = cc.getConstructor("([B)V");
            cm.insertBefore("if(Arrays.equals($1,__h_ok)){$1=__h_nk;System.out.println(\"============================== agent working ==============================\");}");

            return cc.toBytecode();
        } catch (Exception e) {
            throw new IllegalClassFormatException(e.getMessage());
        }
    }
}
