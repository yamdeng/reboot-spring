package com.yamdeng.template.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import com.yamdeng.template.exception.ReqeustParameterException;
import com.yamdeng.template.util.Helper;
import com.yamdeng.template.util.TypeConvertUtil;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ExecuteService {

    private Map<String, CommonRestInfo> restCommonInfoMap = new HashMap<>();

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Validator validator;

    @Value("${app.root-package}")
    String appRootPackage;

    @PostConstruct
    private void init() throws Exception {
        ClassLoader loader = ExecuteService.class.getClassLoader();
        Map<String, Class<?>> dotClassMap = new HashMap<>();
        Reflections reflections = new Reflections(
                    new String[]{appRootPackage + ".dto"}, 
                    new SubTypesScanner(false), loader);
        Set<Class<? extends Object>> dotClassList = reflections.getSubTypesOf(Object.class);
        for (Class<?> dotClass : dotClassList) {
            dotClassMap.put(dotClass.getSimpleName(), dotClass);
        }
        entityManagerFactory.getMetamodel().getEntities().forEach(model -> {
            String entityName = model.getName();
            String uriName = Helper.getRestUriName(entityName);
            String serviceName = Helper.getRestServiceName(entityName);
            Class<?> entityClass = model.getJavaType();
            Class<?> dtoClass = dotClassMap.get(entityName + "Dto");
            restCommonInfoMap.put(uriName, new CommonRestInfo(entityName, uriName, serviceName, entityClass, dtoClass));
        });
        log.info("restCommonInfoMap : " + restCommonInfoMap);
    }

    public List<?> list(String entityNames) {
        CommonRestInfo commonRestInfo = restCommonInfoMap.get(entityNames);
        String serviceName = commonRestInfo.getServiceName();
        RestServiceInterface<?, ?> restServiceInterface = applicationContext.getBean(serviceName, RestServiceInterface.class);
        return restServiceInterface.list();
    }

    public Object getDetail(String entityNames, Long id) {
        CommonRestInfo commonRestInfo = restCommonInfoMap.get(entityNames);
        String serviceName = commonRestInfo.getServiceName();
        RestServiceInterface<?, ?> restServiceInterface = applicationContext.getBean(serviceName, RestServiceInterface.class);
        return restServiceInterface.getDetail(id);
    }

    public Object create(String entityNames, Map<String, Object> body, Errors errors) {
        CommonRestInfo commonRestInfo = restCommonInfoMap.get(entityNames);
        String serviceName = commonRestInfo.getServiceName();
        Class<?> dtoClass = commonRestInfo.getDtoClass();
        RestServiceInterface<Object, Object> restServiceInterface = 
                applicationContext.getBean(serviceName, RestServiceInterface.class);
        Object requestDto = TypeConvertUtil.convertType(body, dtoClass);
        validator.validate(requestDto, errors);
        if(errors.hasErrors()) {
            List<ObjectError> errorObjects = errors.getAllErrors();
            log.error("errorObjects : " + errorObjects);
            throw new ReqeustParameterException(errorObjects);
        }
        return restServiceInterface.create(requestDto);
    }

    public Object update(String entityNames, Long id, Map<String, Object> body, Errors errors) {
        CommonRestInfo commonRestInfo = restCommonInfoMap.get(entityNames);
        String serviceName = commonRestInfo.getServiceName();
        Class<?> dtoClass = commonRestInfo.getDtoClass();
        RestServiceInterface<Object, Object> restServiceInterface = 
                applicationContext.getBean(serviceName, RestServiceInterface.class);
        Object requestDto = TypeConvertUtil.convertType(body, dtoClass);
        validator.validate(requestDto, errors);
        if(errors.hasErrors()) {
            List<ObjectError> errorObjects = errors.getAllErrors();
            log.error("errorObjects : " + errorObjects);
            throw new ReqeustParameterException(errorObjects);
        }
        return restServiceInterface.update(id, requestDto);
    }

    public Long delete(String entityNames, Long id) {
        CommonRestInfo commonRestInfo = restCommonInfoMap.get(entityNames);
        String serviceName = commonRestInfo.getServiceName();
        RestServiceInterface<Object, Object> restServiceInterface = 
                applicationContext.getBean(serviceName, RestServiceInterface.class);
        return restServiceInterface.delete(id);
    }
    
}
