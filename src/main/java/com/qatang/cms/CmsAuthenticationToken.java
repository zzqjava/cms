package com.qatang.cms;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by chirowong on 2014/9/3.
 */
public class CmsAuthenticationToken extends UsernamePasswordToken {
    private String captcha;

    public CmsAuthenticationToken(String username, String password, String captcha, boolean remeberMe){
        super(username, password, remeberMe);
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
