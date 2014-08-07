package com.qatang.cms.validator.impl.role;

import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.role.RoleMenuForm;
import com.qatang.cms.validator.AbstractValidator;
import org.springframework.stereotype.Component;

/**
 * Created by zhangzq on 14-8-6.
 */
@Component
public class CreateRoleMenuValidator extends AbstractValidator<RoleMenuForm> {

    @Override
    public boolean validate(RoleMenuForm roleMenuForm) throws ValidateFailedException {

        if (roleMenuForm.getRoleId() == null || "".equals(roleMenuForm.getRoleId())) {
            String msg = String.format("角色id不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        return true;
    }
}
