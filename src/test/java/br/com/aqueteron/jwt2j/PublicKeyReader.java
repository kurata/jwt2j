package br.com.aqueteron.jwt2j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;

public class PublicKeyReader {

    public static PublicKey get(final String filename, final String algorithmName) throws Exception {

        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
        return loadPublicKey(keyBytes, algorithmName);
    }

    public static PublicKey getFromBase64File(final String fileName, final String algorithmName)
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] fileByteArray = Files.readAllBytes(Paths.get(fileName));
        String fileString = new String(fileByteArray);
        String keyBase64String = fileString.replaceAll("\\n", "")
                .replaceAll("\\r", "")
                .replace("-----BEGIN " + algorithmName + " PUBLIC KEY-----", "")
                .replace("-----END " + algorithmName + " PUBLIC KEY-----", "");
        Decoder decoder = Base64.getDecoder();
        byte[] keyByteArray = decoder.decode(keyBase64String);
        return loadPublicKey(keyByteArray, algorithmName);
    }

    /**
     * @param keyBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static PublicKey loadPublicKey(final byte[] keyBytes, final String algorithmName)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(algorithmName);
        return kf.generatePublic(spec);
    }

}
