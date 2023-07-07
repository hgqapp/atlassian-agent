package io.zhile.crack.atlassian.agent;
import javassist.*;

import java.io.File;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * @author pengzhile
 * @link https://zhile.io
 * @version 1.0
 */
public class KeyTransformer implements ClassFileTransformer {
    private static final String CN_KEY_SPEC = "java/security/spec/EncodedKeySpec";

    private static final String LICENSE_DECODER_PATH = "com/atlassian/extras/decoder/v2/Version2LicenseDecoder";
    private static final String LICENSE_DECODER_CLASS = "com.atlassian.extras.decoder.v2.Version2LicenseDecoder";

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className == null) {
            return classfileBuffer;
        }

        if (className.equals(CN_KEY_SPEC)) {
            return handleKeySpec();
        } else if (className.equals(LICENSE_DECODER_PATH)) {
            return handleLicenseDecoder();
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
                b64f = "java.util.Base64.getDecoder().decode";
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


    /**
     * 移除用于验证哈希的方法: <code>com.atlassian.extras.decoder.v2.Version2LicenseDecoder#verifyLicenseHash</code>
     *
     * @return 修改过的类的字节码
     * @throws IllegalClassFormatException 当某些地方出问题了就会抛出这个异常
     */
    private byte[] handleLicenseDecoder() throws IllegalClassFormatException {
        try {
            // 我不知道怎么从 com.atlassian.bitbucket.internal.launcher.BitbucketServerLauncher 读取这个路径，所以我直接 HARD CODE
            // Forgive me pls...

            Map<String, String> osEnv = System.getenv();
            String atlassianDir = osEnv.get("ATLASSIAN_DIR");
            System.out.println("the ATLASSIAN_DIR is：" + atlassianDir);
            File libs = new File(atlassianDir);
            ClassPool cp = ClassPool.getDefault();

            Arrays.stream(Objects.requireNonNull(libs.listFiles())).map(File::getAbsolutePath).forEach((it) -> {
                try {
                    cp.insertClassPath(it);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            cp.importPackage("com.atlassian.extras.common.LicenseException");
            cp.importPackage("com.atlassian.extras.common.org.springframework.util.DefaultPropertiesPersister");
            cp.importPackage("com.atlassian.extras.decoder.api.AbstractLicenseDecoder");
            cp.importPackage("com.atlassian.extras.decoder.api.LicenseVerificationException");
            cp.importPackage("com.atlassian.extras.keymanager.KeyManager");
            cp.importPackage("com.atlassian.extras.keymanager.SortedProperties");
            cp.importPackage("java.io.ByteArrayInputStream");
            cp.importPackage("java.io.ByteArrayOutputStream");
            cp.importPackage("java.io.DataInputStream");
            cp.importPackage("java.io.DataOutputStream");
            cp.importPackage("java.io.IOException");
            cp.importPackage("java.io.InputStream");
            cp.importPackage("java.io.InputStreamReader");
            cp.importPackage("java.io.OutputStream");
            cp.importPackage("java.io.Reader");
            cp.importPackage("java.io.StringWriter");
            cp.importPackage("java.io.Writer");
            cp.importPackage("java.nio.charset.Charset");
            cp.importPackage("java.nio.charset.StandardCharsets");
            cp.importPackage("java.text.SimpleDateFormat");
            cp.importPackage("java.util.Date");
            cp.importPackage("java.util.Map");
            cp.importPackage("java.util.Properties");
            cp.importPackage("java.util.zip.Inflater");
            cp.importPackage("java.util.zip.InflaterInputStream");
            cp.importPackage("org.apache.commons.codec.binary.Base64");

            CtClass target = cp.getCtClass(LICENSE_DECODER_CLASS);
            CtMethod verifyLicenseHash = target.getDeclaredMethod("verifyLicenseHash");
            verifyLicenseHash.setBody("{System.out.println(\"atlassian-agent: skip license hash check\");}");
            CtMethod checkAndGetLicenseText = target.getDeclaredMethod("checkAndGetLicenseText");

            checkAndGetLicenseText.setBody("        try {\n" +
                    "            byte[] decodedBytes = org.apache.commons.codec.binary.Base64.decodeBase64($1.getBytes(StandardCharsets.UTF_8));\n" +
                    "            ByteArrayInputStream in = new ByteArrayInputStream(decodedBytes);\n" +
                    "            DataInputStream dIn = new DataInputStream(in);\n" +
                    "            int textLength = dIn.readInt();\n" +
                    "            byte[] licenseText = new byte[textLength];\n" +
                    "            dIn.read(licenseText);\n" +
                    "            System.out.println(\"atlassian-agent: skip verify the license.\");\n" +
                    "            return licenseText;\n" +
                    "        } catch (Exception var10) {\n" +
                    "            throw new LicenseException(var10);\n" +
                    "        }");
            return target.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalClassFormatException(e.getMessage());
        }
    }

}