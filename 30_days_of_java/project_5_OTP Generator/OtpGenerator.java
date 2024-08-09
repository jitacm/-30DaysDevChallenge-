package com.otp;

import java.security.SecureRandom;
import java.util.logging.Logger;

public class OtpGenerator {
    private static final String NUMERIC = "0123456789";
    private static final String ALPHANUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom random = new SecureRandom();
    private static final Logger logger = Logger.getLogger(OtpGenerator.class.getName());

    public static String generateOtp(int length, boolean alphanumeric) {
        StringBuilder otp = new StringBuilder(length);
        String characters = alphanumeric ? ALPHANUMERIC : NUMERIC;
        for (int i = 0; i < length; i++) {
            otp.append(characters.charAt(random.nextInt(characters.length())));
        }
        logger.info("Generated OTP: " + otp);
        return otp.toString();
    }

    public static void main(String[] args) {
        String numericOtp = generateOtp(6, false);
        System.out.println("Generated Numeric OTP: " + numericOtp);

        String alphanumericOtp = generateOtp(8, true);
        System.out.println("Generated Alphanumeric OTP: " + alphanumericOtp);

        OtpValidator.storeOtp(numericOtp, 5000);
        System.out.println("Is OTP valid? " + OtpValidator.validateOtp(numericOtp));
    }
}
