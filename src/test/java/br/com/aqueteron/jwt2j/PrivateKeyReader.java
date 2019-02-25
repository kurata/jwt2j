package br.com.aqueteron.jwt2j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;

public class PrivateKeyReader {

    public static PrivateKey get(String filename, final String algorithmName) throws Exception {

        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
        return loadPrivateKey(keyBytes, algorithmName);
    }

    public static PrivateKey getFromBase64File(final String fileName, final String algorithmName)
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] fileByteArray = Files.readAllBytes(Paths.get(fileName));
        String fileString = new String(fileByteArray, "UTF-8");
        String keyBase64String = fileString.replaceAll("\\n", "")
                .replaceAll("\\r", "")
                .replace("-----BEGIN " + algorithmName + " PRIVATE KEY-----", "")
                .replace("-----END " + algorithmName + " PRIVATE KEY-----", "");
        Decoder decoder = Base64.getDecoder();
        byte[] keyByteArray = decoder.decode(keyBase64String);
        return loadPrivateKey(keyByteArray, algorithmName);
    }

    private static PrivateKey loadPrivateKey(final byte[] keyByteArray, final String algorithmName)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyByteArray);
        KeyFactory kf = KeyFactory.getInstance(algorithmName);
        return kf.generatePrivate(spec);
    }

}
