package com.kampus.kbazaar.promotion;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<String> values) {
        return values != null ? String.format("{%s}", String.join(SPLIT_CHAR, values)) : "";
    }

    @Override
    public List<String> convertToEntityAttribute(String value) {
        if (value == null) {
            return emptyList();
        }

        String vs = value.replace("{", "").replace("}", "");
        return Arrays.asList(vs.split(SPLIT_CHAR));
    }
}