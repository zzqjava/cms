package com.qatang.cms.enums.converter;

import com.qatang.cms.enums.Gender;
import org.springframework.core.convert.converter.Converter;

import javax.persistence.AttributeConverter;

/**
 * Created by JSH on 2014/12/27.
 */
public class GenderConverter implements AttributeConverter<Gender, Integer>, Converter<String, Gender> {
    @Override
    public Integer convertToDatabaseColumn(Gender gender) {
        return gender.getValue();
    }

    @Override
    public Gender convertToEntityAttribute(Integer i) {
        return Gender.get(i);
    }

    @Override
    public Gender convert(String s) {
        return Gender.get(Integer.parseInt(s));
    }
}
