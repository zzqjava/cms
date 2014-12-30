package com.qatang.cms.validator.impl.role;

import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.role.RoleResourceForm;
import com.qatang.cms.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by zhangzq on 14-8-6.
 */
@Component
public class CreateRoleResourceValidator extends AbstractValidator<RoleResourceForm> {

    @Override
    public boolean validate(RoleResourceForm roleResourceForm) throws ValidateFailedException {
        if(roleResourceForm == null){
            String msg = String.format("不能提交空表单");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (roleResourceForm.getRoleId() == null) {
            String msg = String.format("角色id不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if(StringUtils.isEmpty(roleResourceForm.getResourceIds())){
            String msg = String.format("没有选择资源");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        return true;
    }
}
