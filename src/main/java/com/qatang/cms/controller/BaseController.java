package com.qatang.cms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qatang on 14-6-5.
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final static String ERROR_MESSAGE_KEY = "errorMessage";
    protected final static String SUCCESS_MESSAGE_KEY = "successMessage";
    protected final static String FORWARD_URL = "forwardUrl";
    protected final static String PAGE_STRING = "pageString";
}
