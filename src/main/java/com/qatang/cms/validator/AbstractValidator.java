package com.qatang.cms.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qatang on 14-6-12.
 */
public abstract class AbstractValidator<T> implements IValidator<T> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
