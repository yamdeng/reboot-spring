package com.yamdeng.template.common;

import java.util.List;

public interface RestServiceInterface<E, T> {

    E create(T dto);

    E update(Long id, T memberDto);

    Long delete(Long id);

    List<E> list();

    E getDetail(Long id);
    
}
