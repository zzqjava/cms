package com.qatang.cms.validator.impl.user;

import com.qatang.cms.entity.user.User;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.Gender;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by JSH on 2014/7/2.
 */
@Component
public class UpdateUserValidator extends AbstractValidator<UserForm> {
    @Autowired
    private UserService userService;
    @Override
    public boolean validate(UserForm userForm) throws ValidateFailedException {
        logger.info("开始验证userForm参数");
        if (userForm == null) {
            String msg = String.format("userForm参数不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (StringUtils.isEmpty(userForm.getId())) {
            String msg = String.format("更新用户，用户id为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        Long id;
        try {
            id = Long.parseLong(userForm.getId());
        } catch (NumberFormatException e) {
            String msg = String.format("id字段不合法");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        User user = userService.get(id);
        if (user == null) {
            String msg = String.format("所要更新的用户不存在");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (StringUtils.isEmpty(userForm.getName())) {
            String msg = String.format("姓名不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (userForm.getName().length() < 2 || userForm.getName().length() > 6) {
            String msg = String.format("姓名长度必须在2-6个字符之间");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (StringUtils.isEmpty(userForm.getEmail())) {
            String msg = String.format("用户邮箱不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (!this.checkEmail(userForm.getEmail())) {
            String msg = String.format("邮箱格式错误");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (StringUtils.isNotEmpty(userForm.getMobile())) {
            if (!this.checkMobile(userForm.getMobile())) {
                String msg = String.format("手机格式错误");
                logger.error(msg);
                throw new ValidateFailedException(msg);
            }
        }
        if (StringUtils.isEmpty(userForm.getGenderValue())) {
            String msg = String.format("性别不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        int genderValue;
        try {
            genderValue = Integer.valueOf(userForm.getGenderValue());
        } catch (Exception e) {
            String msg = String.format("性别字段格式不合法");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        Gender gender = Gender.get(genderValue);
        if (gender == null) {
            String msg = String.format("性别字段格式不合法");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (StringUtils.isEmpty(userForm.getValidValue())) {
            String msg = String.format("是否有效不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        int validValue;
        try {
            validValue = Integer.valueOf(userForm.getValidValue());
        } catch (Exception e) {
            String msg = String.format("是否有效状态字段格式不合法");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        EnableDisableStatus enableDisableStatus = EnableDisableStatus.get(validValue);
        if (enableDisableStatus == null) {
            String msg = String.format("是否有效状态字段格式不合法");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        return true;
    }
}
