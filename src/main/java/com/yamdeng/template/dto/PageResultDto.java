package com.yamdeng.template.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class PageResultDto<T> {

    private Pageable pageable;

    private Integer totalPages;

    private Integer size;

    private Integer totalElements;

    private List<T> content;
    
}
