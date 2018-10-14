package com.danceUpByStas.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

public class PasswordHashing {


    //http://tutorials.jenkov.com/java-cryptography/messagedigest.html
    //https://www.mkyong.com/java/how-do-convert-byte-array-to-string-in-java/
    public String hashPassword(String password, byte[] salt, int iterationCount, int keyLength, String hashingAlgorithm) {

        Logger logger = LogManager.getLogger(this.getClass());
        String hashedPassword = "";
        char[] charPassword = password.toCharArray();

        PBEKeySpec spec = new PBEKeySpec(charPassword, salt, iterationCount, keyLength);
        Arrays.fill(charPassword, Character.MIN_VALUE);

        try {

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(hashingAlgorithm);
            byte[] byteHash = secretKeyFactory.generateSecret(spec).getEncoded();
            hashedPassword = new String(byteHash, "Unicode");

        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {

            logger.debug("No such algorithm exception: {}", noSuchAlgorithmException);
        } catch (InvalidKeySpecException invalidKeyException) {

            logger.debug("Invalid Key Exception: {}", invalidKeyException);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {

            logger.debug("Unsupported encoding: {}", unsupportedEncodingException);

        } finally {

            spec.clearPassword();
        }


        return hashedPassword;

    }

    private byte[] getSalt(int length) {

        Random random = new SecureRandom();
        byte[] salt = new byte[length];
        random.nextBytes(salt);

        return salt;
    }
}
