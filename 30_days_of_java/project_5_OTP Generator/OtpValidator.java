package com.otp;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class OtpValidator {
    private static final Map<String, Long> otpStorage = new HashMap<>();
    private static final Logger logger = Logger.getLogger(OtpValidator.class.getName());

    public static void storeOtp(String otp, long validityPeriod) {
        long expiryTime = System.currentTimeMillis() + validityPeriod;
        otpStorage.put(otp, expiryTime);
        logger.info("Stored OTP: " + otp + " with expiry time: " + expiryTime);
    }

    public static boolean validateOtp(String otp) {
        Long expiryTime = otpStorage.get(otp);
        if (expiryTime == null) {
            return false;
        }
        if (System.currentTimeMillis() > expiryTime) {
            otpStorage.remove(otp);
            logger.warning("OTP expired: " + otp);
            return false;
        }
        otpStorage.remove(otp);
        logger.info("OTP validated: " + otp);
        return true;
    }
}
