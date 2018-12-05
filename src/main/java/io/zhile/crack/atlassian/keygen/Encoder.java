package io.zhile.crack.atlassian.keygen;

import io.zhile.crack.atlassian.utils.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.zip.Deflater;
import java.util.zip.DeflaterInputStream;

/**
 * @author pengzhile
 * @version 1.0
 * @link https://zhile.io
 */
public class Encoder {
    private static final String PRIVATE_KEY_STR = "MIIBSwIBADCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoEFgIUNYsbkapILzW8VhfGrU4eHo6/Dqw=";
    private static final PrivateKey PRIVATE_KEY;

    static {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("DSA");
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.decode(PRIVATE_KEY_STR));
            PRIVATE_KEY = keyFactory.generatePrivate(privateKeySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encode(String licenseText) throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        byte[] licenseData = zipText(licenseText.getBytes());
        byte[] text = new byte[licenseData.length + 5];
        text[0] = 13;   // license prefix
        text[1] = 14;
        text[2] = 12;
        text[3] = 10;
        text[4] = 15;
        System.arraycopy(licenseData, 0, text, 5, licenseData.length);

        Signature signature = Signature.getInstance("SHA1withDSA");
        signature.initSign(PRIVATE_KEY);
        signature.update(text);

        return packLicense(text, signature.sign());
    }

    private static byte[] zipText(byte[] licenseText) throws IOException {
        int len;
        byte[] buff = new byte[64];
        ByteArrayInputStream in = new ByteArrayInputStream(licenseText);
        DeflaterInputStream deflater = new DeflaterInputStream(in, new Deflater());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            while ((len = deflater.read(buff)) > 0) {
                out.write(buff, 0, len);
            }

            return out.toByteArray();
        } finally {
            out.close();
            deflater.close();
            in.close();
        }
    }

    private static String packLicense(byte[] text, byte[] hash) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dOut = new DataOutputStream(out);
        dOut.writeInt(text.length);
        dOut.write(text);
        dOut.write(hash);

        String result = Base64.encode(out.toByteArray()).trim();
        return split(result + "X02" + Integer.toString(result.length(), 31));
    }

    private static String split(String licenseData) {
        if (licenseData == null || licenseData.length() <= 0) {
            return licenseData;
        }

        char[] chars = licenseData.toCharArray();
        StringBuilder buf = new StringBuilder(chars.length + chars.length / 76);

        for (int i = 0; i < chars.length; ++i) {
            buf.append(chars[i]);
            if (i > 0 && i % 76 == 0) {
                buf.append('\n');
            }
        }

        return buf.toString();
    }
}
