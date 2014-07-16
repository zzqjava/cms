package com.qatang.cms.validator.impl.role;

import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.role.RoleForm;
import com.qatang.cms.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by zhangzq on 14-6-25.
 */
@Component
public class CreateRoleValidator extends AbstractValidator<RoleForm> {

    @Override
    public boolean validate(RoleForm roleForm) throws ValidateFailedException {
        if (StringUtils.isEmpty(roleForm.getRoleName())) {
            String msg = String.format("角色名称不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        return true;
    }
}
