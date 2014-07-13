package com.qatang.cms.validator.impl.user;

import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.Gender;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by JSH on 14-7-3.
 */
@Component
public class QueryUserValidator extends AbstractValidator<UserForm> {
    @Override
    public boolean validate(UserForm userForm) throws ValidateFailedException {
        logger.info("开始验证userForm参数");
        if (userForm == null) {
            String msg = String.format("userForm参数不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (StringUtils.isNotEmpty(userForm.getGenderValue())) {
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
        }
        if (StringUtils.isNotEmpty(userForm.getValidValue())) {
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
        }
        return true;
    }
}
