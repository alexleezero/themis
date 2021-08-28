/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.container.provider;

import com.maishare.themis.common.exception.ThemisException;
import com.maishare.themis.config.ThemisConfig;
import com.maishare.themis.context.ThemisContext;
import com.maishare.themis.context.domain.LibraFile;
import com.maishare.themis.context.domain.LibraInstance;
import com.maishare.themis.context.testcase.DefaultTestCase;
import com.maishare.themis.context.testcase.ThemisTestCase;
import com.maishare.themis.extension.ExtensionLoader;
import com.maishare.themis.parser.LibraParser;
import com.maishare.themis.parser.locator.LibraFileLocator;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 默认的测试用例提供器实现
 * 1.根据指定文件路径扫描当前工程中所有的测试用例，并翻译成libra data和libra logic数据结构
 * 2.根据用户传递的激活测试用例索引参数匹配指定的libra logic和libra data形成待执行用例数据结构
 * @author lijian
 * @version @Id: DefaultTestCaseProvider.java, v 0.1 2020年01月17日 14:38 lijian Exp $
 */
public class DefaultTestCaseProvider implements ThemisTestCaseProvider {

    private ThemisConfig themisConfig;

    public DefaultTestCaseProvider(ThemisConfig themisConfig) {
        this.themisConfig = themisConfig;
    }

    /** 激活的测试用例索引 */
    private List<String> activateTestCaseIndexes = new ArrayList<>();

    /**
     * {@link ThemisTestCaseProvider#activateTestCaseIndex(String)}
     */
    @Override
    public void activateTestCaseIndex(String testCaseIndex) {
        activateTestCaseIndexes.add(testCaseIndex);
    }

    /**
     * {@link ThemisTestCaseProvider#activateTestCaseIndexes(String...)}
     */
    @Override
    public void activateTestCaseIndexes(String... testCaseIndex) {
        this.activateTestCaseIndexes.addAll(Arrays.asList(testCaseIndex));
    }

    @Override
    public void activateTestCaseIndexes(List testCaseIndexes) {
        this.activateTestCaseIndexes.addAll(testCaseIndexes);
    }

    /**
     * libra对象生成核心逻辑
     */
    protected List<LibraInstance> supply(ThemisContext themisContext) {
        //调用locator扫描文件系统，获取themis框架需要的文件信息
        List<LibraFile> libraFiles = new LibraFileLocator(themisContext.getThemisConfig()).lookup();

        //调用parser parse解析， 生成List<Libra>
        //注意点: 通过themisContext的类型判断是实例化spring test execution还是通用的execution
        LibraParser libraParser = ExtensionLoader.getExtensionLoader(LibraParser.class).getAdaptiveExtension();

        return libraParser.parse(themisContext, libraFiles).stream()
                .flatMap(libra -> libra.getLibraInstanceList().stream())
                .collect(Collectors.toList());
    }


    @Override
    public Map<String, ThemisTestCase> produce(ThemisContext themisContext) {
        //供给用例
        List<LibraInstance> suppliedLibraInstances = supply(themisContext);

        Map<String, ThemisTestCase> testCaseMap = new HashMap<>();
        
        suppliedLibraInstances.stream()
            .forEach(libraInstance -> {
            if (testCaseMap.get(libraInstance.getIndex()) != null) {
                throw new ThemisException(String.format("重复的用例：index[%s]", libraInstance.getIndex()));
            }

            testCaseMap.put(
                    libraInstance.getIndex(),
                    new DefaultTestCase(libraInstance.getThemisTestExecution().getLibraData().getIndex(), libraInstance)
            );
        });

        //填充用例
        populateTestCases(themisContext, testCaseMap);

        return supplyLauncherTestCases(suppliedLibraInstances, testCaseMap);
    }



    /**
     * 填充用例
     */
    protected void populateTestCases(ThemisContext themisContext,
                                     Map<String, ThemisTestCase> themisTestCases) {
        themisContext.populateTestCases(themisTestCases);
    }

    private Map<String, ThemisTestCase> supplyLauncherTestCases(List<LibraInstance> suppliedLibraInstances, Map<String, ThemisTestCase> themisTestCaseMap) {
        if (CollectionUtils.isEmpty(this.activateTestCaseIndexes))
            return themisTestCaseMap;

        List<LibraInstance> launcherLibraInstances = suppliedLibraInstances.stream()
                .filter(libraInstance -> this.activateTestCaseIndexes.contains(libraInstance.getIndex()))
                .collect(Collectors.toList());

        Map<String, ThemisTestCase> launcherTestCaseMap = new HashMap<>();
        launcherLibraInstances.stream()
                .forEach(libraInstance -> launcherTestCaseMap.put(
                        libraInstance.getIndex(),
                        new DefaultTestCase(libraInstance.getThemisTestExecution().getLibraData().getIndex(), libraInstance)
                ));

        return launcherTestCaseMap;
    }

}
