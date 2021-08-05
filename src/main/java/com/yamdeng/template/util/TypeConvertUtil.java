package com.yamdeng.template.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class TypeConvertUtil {

    private static ModelMapper modelMapper = new ModelMapper();

    public static <T, R extends Object> R convertType(T source, Class<R> type) {
        return modelMapper.map(source, type);
    }

    public static <E, R> List<R> convertListToDto(List<E> sourceList, Class<R> dtoType) {
        List<R> resultList = sourceList.stream()
                .map(entity -> modelMapper.map(entity, dtoType))
                .collect(Collectors.toList());
        return resultList;
    }
    
}
