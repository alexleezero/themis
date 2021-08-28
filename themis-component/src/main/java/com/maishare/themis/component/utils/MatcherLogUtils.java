package com.maishare.themis.component.utils;

import com.maishare.themis.component.base.checker.AssertMatcher;
import com.maishare.themis.component.base.checker.MatcherContext;
import org.apache.commons.lang3.CharUtils;
import org.bravo.gaia.log.logger.Logger;
import org.bravo.gaia.log.logger.LoggerFactory;

import static java.lang.String.format;

public class MatcherLogUtils {

    public static void consoleLog(MatcherContext matcherContext, AssertMatcher assertMatcher){
        Logger LOG = LoggerFactory.getLogger(assertMatcher.getClass());
        LOG.info(CharUtils.LF+String.format("【断言匹配器】:%s", assertMatcher.getClass().getName())+CharUtils.LF);
        LOG.info(CharUtils.LF+format("【断言内容】:%s, %s, %s ",
                matcherContext.getExpected(),
                matcherContext.getCompareOperator(),
                matcherContext.getActual()) + CharUtils.LF);
    }
}
