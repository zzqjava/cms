package com.qatang.cms.validator.impl.resource;

import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.YesNoStatus;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.resource.ResourceForm;
import com.qatang.cms.form.role.RoleForm;
import com.qatang.cms.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by lkp on 14-12-31.
 */
@Component
public class QueryResourceValidator extends AbstractValidator<ResourceForm> {
    @Override
    public boolean validate(ResourceForm resourceForm) throws ValidateFailedException {
        logger.info("开始验证resourceForm参数");
        if (resourceForm == null) {
            String msg = String.format("resourceForm参数不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (StringUtils.isNotEmpty(resourceForm.getValidValue())) {
            int valid;
            try {
	            valid = Integer.valueOf(resourceForm.getValidValue());
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
        return true;
    }
}
