package com.qatang.cms.validator.impl.user;

import com.qatang.cms.entity.user.User;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by JSH on 2014/7/2.
 */
@Component
public class UpdateUserValidator extends AbstractValidator<User> {
    @Autowired
    private UserService userService;
    @Override
    public boolean validate(User updateUser) throws ValidateFailedException {
        logger.info("开始验证userForm参数");
        if (updateUser == null) {
            String msg = String.format("userForm参数不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (updateUser.getId() == null) {
            String msg = String.format("更新用户，用户id为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        User user = userService.get(updateUser.getId());
        if (user == null) {
            String msg = String.format("所要更新的用户不存在");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (StringUtils.isEmpty(updateUser.getName())) {
            String msg = String.format("姓名不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (StringUtils.isNotEmpty(updateUser.getEmail())) {
            if (!this.checkEmail(updateUser.getEmail())) {
                String msg = String.format("邮箱格式错误");
                logger.error(msg);
                throw new ValidateFailedException(msg);
            }
        }
        return true;
    }
}
