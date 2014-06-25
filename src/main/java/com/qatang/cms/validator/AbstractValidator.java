package com.qatang.cms.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by qatang on 14-6-12.
 */
public abstract class AbstractValidator<T> implements IValidator<T> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected boolean checkEmail(String email){
        boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            logger.error("验证邮箱地址错误");
        }
        return flag;
    }
}
