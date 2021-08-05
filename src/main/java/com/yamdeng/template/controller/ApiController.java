package com.yamdeng.template.controller;

import java.util.List;
import java.util.Map;

import com.yamdeng.template.common.ExecuteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ExecuteService executeService;
    
    @GetMapping("/{entityNames}")
    public List<?> list(@PathVariable String entityNames) {
        return executeService.list(entityNames);
    }

    @GetMapping("/{entityNames}/{id}")
    public Object getDetail(@PathVariable String entityNames, @PathVariable Long id) {
        return executeService.getDetail(entityNames, id);
    }

    @PostMapping("/{entityNames}")
    public Object create(@PathVariable String entityNames, @RequestBody Map<String, Object> bodyMap, Errors errors) {
        return executeService.create(entityNames, bodyMap, errors);
    }

    @PutMapping("/{entityNames}/{id}")
    public Object update(@PathVariable String entityNames, @PathVariable Long id, @RequestBody Map<String, Object> bodyMap, Errors errors) {
        return executeService.update(entityNames, id, bodyMap, errors);
    }

    @DeleteMapping("/{entityNames}/{id}")
    public Long delete(@PathVariable String entityNames, @PathVariable Long id) {
        return executeService.delete(entityNames, id);
    }

}
