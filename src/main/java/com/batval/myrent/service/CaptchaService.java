package com.batval.myrent.service;

/**
 * Code verification interface
 *
 * @author Baturo Valery
 * @version 1.0
 */
public interface CaptchaService {
    /**
     * Verify captcha
     *
     * @param ip address
     * @param recaptchaResponse response captcha
     */
    String verifyRecaptcha(String ip, String recaptchaResponse);
}
