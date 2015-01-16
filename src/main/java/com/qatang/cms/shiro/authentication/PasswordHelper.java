package com.qatang.cms.shiro.authentication;

import com.qatang.cms.entity.user.User;
import com.qatang.cms.shiro.utils.AuthenticatorUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by qatang on 14/12/2.
 */
@Component
public class PasswordHelper {
    @Value("${password.algorithmName}")
    private String algorithmName = "md5";
    @Value("${password.hashIterations}")
    private int hashIterations = 1;

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public boolean validatePassword(String inputPassword, User user) {
        String inputPasswordHashValue = AuthenticatorUtils.generateHashValue(algorithmName, inputPassword, this.getSalt(user), hashIterations).toString();
        return user.getPassword().equals(inputPasswordHashValue);
    }

    public void encryptPassword(User user) {
        String randomSalt = AuthenticatorUtils.generateRandomSaltValue();
        user.setSalt(randomSalt);

        String passwordHashValue = AuthenticatorUtils.generateHashValue(algorithmName, user.getPassword(), this.getSalt(user), hashIterations).toString();//toString == toHex；there is anther method: toBase64
        user.setPassword(passwordHashValue);
    }

    public String getSalt(User user) {
        return user.getUsername() + user.getSalt();
    }
}
