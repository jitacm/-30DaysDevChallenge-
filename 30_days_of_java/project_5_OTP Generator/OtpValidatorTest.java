package com.otp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OtpValidatorTest {

    @Test
    public void testValidateOtp() throws InterruptedException {
        String otp = OtpGenerator.generateOtp(6, false);
        OtpValidator.storeOtp(otp, 5000); // 5 seconds validity

        assertTrue(OtpValidator.validateOtp(otp));
        assertFalse(OtpValidator.validateOtp(otp)); // OTP should be removed after validation

        OtpValidator.storeOtp(otp, 1000); // 1 second validity
        Thread.sleep(1100); // Wait for OTP to expire
        assertFalse(OtpValidator.validateOtp(otp));
    }
}