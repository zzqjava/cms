package com.qatang.cms.filter;

import com.qatang.cms.CmsAuthenticationToken;
import com.qatang.cms.IncorrectCaptchaException;
import com.qatang.cms.constants.CommonConstants;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by chirowong on 2014/9/3.
 */
public class AuthenticationFilter extends FormAuthenticationFilter {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    @Override
    protected CmsAuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse){
        String username = getUsername(servletRequest);
        String password = getPassword(servletRequest);
        String captcha =  getCaptcha(servletRequest);
        boolean rememberMe = isRememberMe(servletRequest);
        return new CmsAuthenticationToken(username,password, captcha, rememberMe);
    }

    /**
     * 登录验证
     */
    @Override
    protected boolean executeLogin(ServletRequest request,
                                   ServletResponse response) throws Exception {
        CmsAuthenticationToken token = createToken(request, response);
        try {
            /*图形验证码验证*/
            doCaptchaValidate((HttpServletRequest) request, token);
            Subject subject = getSubject(request, response);
            subject.login(token);//正常验证
            logger.info(token.getUsername()+"登录成功");
            return onLoginSuccess(token, subject, request, response);
        }catch (AuthenticationException e) {
            logger.info(token.getUsername()+"登录失败--"+e);
            return onLoginFailure(token, e, request, response);
        }
    }

    // 验证码校验
    protected void doCaptchaValidate(HttpServletRequest request, CmsAuthenticationToken token) {
        //session中的图形码字符串
        String captcha = (String) request.getSession().getAttribute(CommonConstants.KAPTCHA_SESSION_KEY);
        //比对
        if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
            throw new IncorrectCaptchaException("验证码错误！");
        }
    }

    protected String getCaptcha(ServletRequest paramServletRequest){
        return WebUtils.getCleanParam(paramServletRequest, getCaptchaParam());
    }

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }
}
