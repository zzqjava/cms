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
public class RoleFormTypeConverterValidator extends AbstractValidator<RoleForm> {

    @Override
    public boolean validate(RoleForm roleForm) throws ValidateFailedException {

        if (StringUtils.isNotEmpty(roleForm.getId())) {
            Long id = null;
            try {
                id = Long.parseLong(roleForm.getId());
            } catch (NumberFormatException e) {
                String msg = String.format("角色id不合法！");
                logger.error(msg + e.getMessage());
                throw new ValidateFailedException(msg);
            }
        }
        return true;
    }
}
