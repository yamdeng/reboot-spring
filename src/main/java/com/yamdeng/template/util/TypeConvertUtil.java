package com.yamdeng.template.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class TypeConvertUtil {

    private static ModelMapper modelMapper = new ModelMapper();

    public static <T, R> R convertType(T target, Class<R> type) {
        return modelMapper.map(target, type);
    }

    public static <E, R> List<R> convertListToDto(List<E> targetList, Class<R> dtoType) {
        List<R> resultList = targetList.stream()
                .map(entity -> modelMapper.map(entity, dtoType))
                .collect(Collectors.toList());
        return resultList;
    }
    
}
