package com.otp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OtpGeneratorTest {

    @Test
    public void testGenerateNumericOtp() {
        String otp = OtpGenerator.generateOtp(6, false);
        assertEquals(6, otp.length());
        assertTrue(otp.matches("\\d{6}"));
    }

    @Test
    public void testGenerateAlphanumericOtp() {
        String otp = OtpGenerator.generateOtp(8, true);
        assertEquals(8, otp.length());
        assertTrue(otp.matches("[a-zA-Z0-9]{8}"));
    }
}