/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context.registry;

import com.maishare.themis.common.enums.LibraCompType;
import com.maishare.themis.common.exception.LibraCompException;
import com.maishare.themis.context.ThemisContext;
import com.maishare.themis.context.component.LibraComponent;
import com.maishare.themis.extension.ExtensionLoader;
import com.maishare.themis.extension.utils.CollectionUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 默认组件注册中心实现
 * @author moushaokun
 * @version @Id: DefaultCompRegistry.java, v 0.1 2020年03月11日 11:09 moushaokun Exp $
 */
public class DefaultCompRegistry implements LibraCompRegistry {

    @Getter
    @Setter
    private List<LibraComponent> libraComponents = new ArrayList<>();
    
    private final Map<String,LibraComponent> cachedComponent = new ConcurrentHashMap<>();

    @Override
    public void registry(ThemisContext themisContext) {
        Set<LibraComponent> libraComponentList
            = ExtensionLoader.getExtensionLoader(LibraComponent.class).getSupportedExtensionInstances();
    
        if (CollectionUtils.isNotEmpty(libraComponentList)) {
            libraComponents = libraComponentList.stream().collect(Collectors.toList());
        }
    }

    @Override
    public LibraComponent retrieve(LibraCompType compType, String compName) {
    
        String key = compType.getTypeCode() + "_" + compName;
    
        LibraComponent libraComponent = cachedComponent.get(key);
    
        if (null != libraComponent) {
            return libraComponent;
        }
    
        for (LibraComponent component : libraComponents) {
            if (component.type() == compType && component.name().equals(compName)) {
                cachedComponent.put(key, component);
            
                return component;
            }
        }
        throw new LibraCompException(
            String.format("组件异常：未找到类型为【%s】，名称为【%s】的组件",
                compType.getTypeName(), compName));
    }
}

