package com.batval.myrent.service.impl;

import com.batval.myrent.service.CaptchaService;
import com.batval.myrent.util.RecaptchaUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Code verification service
 * Implementation of {@link CaptchaService} interface
 *
 * @author Baturo Valery
 * @version 1.0
 */
public class CaptchaServiceImpl implements CaptchaService {

    /**
     * Event Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(CaptchaServiceImpl.class);

    /**
     * Secret captcha
     */
    @Value("${google.recaptcha.key.secret}")
    String recaptchaSecret;
    /**
     * Google captcha verify url
     */
    private static final String GOOGLE_RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    /**
     * Template
     */
    @Autowired
    RestTemplate restTemplate;

    /**
     * Verify captcha
     *
     * @param ip address
     * @param recaptchaResponse response captcha
     */
    @Override
    public String verifyRecaptcha(String ip, String recaptchaResponse) {
        Map<String, String> body = new HashMap<>();
        body.put("secret", recaptchaSecret);
        body.put("response", recaptchaResponse);
        body.put("remoteip", ip);
        ResponseEntity<Map> recaptchaResponseEntity =
                restTemplate.postForEntity(GOOGLE_RECAPTCHA_VERIFY_URL +
                                "?secret={secret}&response={response}&remoteip={remoteip}",
                        body, Map.class, body);


        Map<String, Object> responseBody =
                recaptchaResponseEntity.getBody();

        boolean recaptchaSucess = (Boolean) responseBody.get("success");
        if (!recaptchaSucess) {
            List<String> errorCodes =
                    (List) responseBody.get("error-codes");

            String errorMessage = errorCodes.stream()
                    .map(s -> RecaptchaUtil.RECAPTCHA_ERROR_CODE.get(s))
                    .collect(Collectors.joining(", "));

logger.error(errorMessage);
            return errorMessage;
        } else {
            logger.error("Captcha successfully entered");
            return StringUtils.EMPTY;
        }
    }
}
