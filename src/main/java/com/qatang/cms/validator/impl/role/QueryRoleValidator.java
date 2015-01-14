package com.qatang.cms.validator.impl.role;

import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.YesNoStatus;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.role.RoleForm;
import com.qatang.cms.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by lkp on 14-12-31.
 */
@Component
public class QueryRoleValidator extends AbstractValidator<RoleForm> {
    @Override
    public boolean validate(RoleForm roleForm) throws ValidateFailedException {
        logger.info("开始验证roleForm参数");
        if (roleForm == null) {
            String msg = String.format("roleForm参数不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (StringUtils.isNotEmpty(roleForm.getValid())) {
            int valid;
            try {
	            valid = Integer.valueOf(roleForm.getValid());
            } catch (Exception e) {
                String msg = String.format("是否有效状态字段格式不合法");
                logger.error(msg);
                throw new ValidateFailedException(msg);
            }
	        EnableDisableStatus enableDisableStatus = EnableDisableStatus.get(valid);
	        if (enableDisableStatus == null) {
		        String msg = String.format("是否有效状态字段格式不合法");
		        logger.error(msg);
		        throw new ValidateFailedException(msg);
	        }
        }
        if (StringUtils.isNotEmpty(roleForm.getIsDefault())) {
            int isDefault;
            try {
	            isDefault = Integer.valueOf(roleForm.getIsDefault());
            } catch (Exception e) {
                String msg = String.format("是否默认角色状态字段格式不合法");
                logger.error(msg);
                throw new ValidateFailedException(msg);
            }
            YesNoStatus yesNoStatus = YesNoStatus.get(isDefault);
            if (yesNoStatus == null) {
                String msg = String.format("是否默认角色状态字段格式不合法");
                logger.error(msg);
                throw new ValidateFailedException(msg);
            }
        }
        return true;
    }
}
