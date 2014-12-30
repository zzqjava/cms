package com.qatang.cms.validator.impl.resource;

import com.qatang.cms.entity.resource.Resource;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.ResourcesType;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.resource.ResourceForm;
import com.qatang.cms.service.resource.ResourceService;
import com.qatang.cms.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by likunpeng on 2014/6/26.
 */
@Component
public class ResourceUpdateValidator extends AbstractValidator<ResourceForm> {

    @Autowired
    private ResourceService resourceService;

	public boolean validate(ResourceForm resourceForm) throws ValidateFailedException {
		logger.info("开始验证resourceForm参数");
		if (resourceForm == null || resourceForm.getId() == null) {
            String msg = String.format("资源对象不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
		}
		if (StringUtils.isNotEmpty(resourceForm.getId())) {
			try {
                Long.parseLong(resourceForm.getId());
			} catch (NumberFormatException e) {
				String msg = String.format("id字段不合法");
				logger.error(msg);
				throw new ValidateFailedException(msg);
			}
		}
		if (StringUtils.isEmpty(resourceForm.getName())) {
			String msg = String.format("资源名不能为空");
			logger.error(msg);
			throw new ValidateFailedException(msg);
		}

		if (StringUtils.isNotEmpty(resourceForm.getPriority())) {
			try {
				Integer.valueOf(resourceForm.getPriority());
			} catch (Exception e) {
				String msg = String.format("资源排序值字段格式不合法");
				logger.error(msg);
				throw new ValidateFailedException(msg);
			}
		} else {
			resourceForm.setPriority("0");
		}
		if (StringUtils.isEmpty(resourceForm.getValidValue())) {
			String msg = String.format("是否有效状态不能为空");
			logger.error(msg);
			throw new ValidateFailedException(msg);
		}
		int validValue = 0;
		try {
			validValue = Integer.valueOf(resourceForm.getValidValue());
		} catch (Exception e) {
			String msg = String.format("是否有效字段格式不合法");
			logger.error(msg);
			throw new ValidateFailedException(msg);
		}
		EnableDisableStatus enableDisableStatus = EnableDisableStatus.get(validValue);
		if (enableDisableStatus == null) {
			String msg = String.format("是否有效字段格式不合法");
			logger.error(msg);
			throw new ValidateFailedException(msg);
		}
        if (StringUtils.isEmpty(resourceForm.getType()) && ResourcesType.ALL.getValue() == ResourcesType.get(Integer.parseInt(resourceForm.getType())).getValue()) {
            String msg = String.format("必须选择资源类型");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (StringUtils.isEmpty(resourceForm.getIdentifier())) {
            String msg = String.format("必须填写权限");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }

        //如果id不为空时，进行修改验证
        if (resourceForm.getId() != null) {
            Resource resource = resourceService.get(Long.parseLong(resourceForm.getId()));
            if (resource == null) {
                logger.error("根据资源id,没有查询到该资源信息！");
                String msg = String.format("根据资源id,没有查询到该资源信息！");
                logger.error(msg);
                throw new ValidateFailedException(msg);
            }
        }
		return true;
	}
}
