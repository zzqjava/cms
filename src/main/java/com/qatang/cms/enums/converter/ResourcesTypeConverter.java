package com.qatang.cms.enums.converter;

import com.qatang.cms.enums.ResourcesType;
import com.qatang.cms.enums.YesNoStatus;
import org.springframework.core.convert.converter.Converter;

import javax.persistence.AttributeConverter;

/**
 * Created by zhangzq on 14-12-23.
 */
public class ResourcesTypeConverter implements AttributeConverter<ResourcesType, Integer>, Converter<String, ResourcesType> {

    @Override
    public Integer convertToDatabaseColumn(ResourcesType resourcesType) {
        return resourcesType.getValue();
    }

    @Override
    public ResourcesType convertToEntityAttribute(Integer integer) {
        return ResourcesType.get(integer);
    }

    @Override
    public ResourcesType convert(String s) {
        return ResourcesType.get(Integer.parseInt(s));
    }
}
