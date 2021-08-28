/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.locator;

import com.google.common.collect.Lists;
import com.maishare.themis.config.ThemisConfig;
import com.maishare.themis.context.domain.LibraFile;
import com.maishare.themis.extension.utils.ClassUtils;
import com.maishare.themis.parser.support.LibraFileResolverHandler;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * libra文件定位器
 * @author hejianbing
 * @version @Id: LibraFileLocator.java, v 0.1 2020年03月10日 15:43 hejianbing Exp $
 */
public class LibraFileLocator implements LibraLocator {
    
    private ThemisConfig themisConfig;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LibraFileLocator.class);
    
    
    public LibraFileLocator(ThemisConfig themisConfig) {
        this.themisConfig = themisConfig;
    }

    @Override
    public List<LibraFile> lookup() {
        List<LibraFile> libraFileList = new ArrayList<>();
        try {
            //存放libra 测试文件一级目录
            ClassLoader classLoader = ClassUtils.getClassLoader(LibraFileLocator.class);
    
            URL location = classLoader.getResource(themisConfig.getTestLibraFileLocation());
    
            File root = new File(location.toURI());
        
            if (!root.exists()) {
                return Lists.newArrayList();
            }
            // 找到libra第二级目录,目录名称为class全限定名
            File[] classNameFiles = root.listFiles((file) -> file.isDirectory());
        
            for (File classNameFile : classNameFiles) {
                String className = classNameFile.getName();
    
                Class<?> aClass = getClass(className);
                if (null == aClass) {
                    LOGGER.debug("libra 测试【{}】类未找到", className);
                    continue;
                }
                
                // 找到libra第三级目录，找到所有方法文件目录
                File[] methodFileList = classNameFile.listFiles((file) -> file.isDirectory());
    
                if (ArrayUtils.isEmpty(methodFileList)) {
                    LOGGER.debug("libra 测试类【{}】未包含需要测试的方法", className);
                    continue;
                }
                
                List<LibraFile> libraFiles = createLibraFile(aClass, methodFileList);

                if (CollectionUtils.isNotEmpty(libraFiles)) {
                    libraFileList.addAll(libraFiles);
                }
            }
        } catch (Exception e) {
            LOGGER.error("libraFile测试资源文件没有找到："+e.getMessage());
        }
        return libraFileList;
    }
    
    /**
     * 创建libraFile文件
     */
    private List<LibraFile> createLibraFile(Class<?> clazz, File[] methodFileList) {
       
        List<LibraFile> libraFileList = new ArrayList<>();
        
        List<Method> methods = getMethods(clazz);
    
        for (File methodFile : methodFileList) {
            String methodName = methodFile.getName();
    
            Method method = getMatcherMethod(methods, methodName);
            if (method == null) {
                LOGGER.debug("libra 测试【{}】类下声明的方法{}不存在", clazz.getName(), methodName);
                continue;
            }
            LibraFile libraFile = new LibraFile();
            libraFile.setFullName(clazz.getName());
            libraFile.setClassName(clazz.getSimpleName());
            libraFile.setMethodName(methodName);
            libraFile.setIndex(clazz.getName()+"."+methodName);
            
            // 找到测试方法下的mock,prepare,clean,check 文件夹及 data,logic 文件,组装数据生成Libra typeFile
            File[] libraTypeFileList = methodFile.listFiles();
        
            for (File libraTypeFile : libraTypeFileList) {
    
                LibraFileResolverHandler libraFileHandle =
                    LibraFileResolverHandler.getLibraFileHandle(libraTypeFile);
    
                if (libraTypeFile.isDirectory()) {
                    libraFileHandle.resolveLibraFile(libraFile, libraTypeFile.listFiles((f) -> f.isFile()));
                } else {
                    libraFileHandle.resolveLibraFile(libraFile, libraTypeFile);
                }
            }
    
            libraFileList.add(libraFile);
        }
        return libraFileList;
    }
    
    /**
     * 获取class 所有方法
     */
    private List<Method> getMethods(Class<?> clazz) {
        try {
            Method[] methods = clazz.getMethods();
            
            return Arrays.asList(methods);
        } catch (Exception e) {
        }
        return Lists.newArrayList();
    }
    
    /**
     * 获取匹配的方法
     */
    private Method getMatcherMethod(List<Method> methods , String methodName) {
        return methods.stream().filter(m -> m.getName().equals(methodName))
            .findAny().orElseGet(()->null);
    }
    
    /**
     *获取clazz
     */
    private Class<?> getClass(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            
            return clazz;
        } catch (Exception e) {
            LOGGER.error(String.format("Libra测试文件包名未找到对应的class信息:%s", className));
        }
        return null;
    }
    
    
}

