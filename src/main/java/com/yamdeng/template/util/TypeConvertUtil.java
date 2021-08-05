package com.yamdeng.template.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class TypeConvertUtil {

    private static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
    }

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
