/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.executor;

import com.maishare.themis.common.exception.FileParseException;
import com.maishare.themis.common.exception.LibraExpressionException;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.parser.executor.context.ExecuteContext;
import com.maishare.themis.parser.libra.LibraLexer;
import com.maishare.themis.parser.libra.LibraParser;
import lombok.Getter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.time.Duration;
import java.util.Date;

/**
 * libra antlr 执行器
 * @author moushaokun
 * @version @Id: LibraAntlrExecutor.java, v 0.1 2020年03月16日 14:03 moushaokun Exp $
 */
public class LibraAntlrExecutor implements LibraExecutor{
    private static final Logger                      LOGGER          = LoggerFactory
        .getLogger(LibraAntlrExecutor.class);

    /** 预留操作: 在主逻辑执行完成之后执行，且一定会执行 */
    @Getter
    private static final ThreadLocal<ExecuteContext> reservedContext = ThreadLocal
        .withInitial(ExecuteContext::new);

    private static void resetReservedContext() {
        reservedContext.remove();
        reservedContext.set(new ExecuteContext());
    }

    @Override
    public void execute(ThemisTestExecution themisTestExecution) {
        CharStream input;
        try {
            StringReader stringReader = new StringReader(
                themisTestExecution.getLibraFile().getLibraLogicFile().getOriginal());
            input = CharStreams.fromReader(stringReader);
        } catch (IOException e) {
            throw new FileParseException("读取libra文件失败", e);
        }

        LibraLexer libraLexer = new LibraLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(libraLexer);

        LibraParser libraParser = new LibraParser(tokens);


        ExecuteContext localContext = reservedContext.get();

        //设置当前逻辑文件深度
        localContext.setDepth(localContext.getDepth() + 1);

        Date beginTime = new Date();

        beginLog(themisTestExecution);

        try{
            //执行逻辑文件
            new LibraAntlrVisitor(themisTestExecution).visit(libraParser.file());

        } catch (Throwable e){
            throw new LibraExpressionException("执行logic文件出错" , e);

        } finally {
            if(localContext.getDepth() == 1){
                //最上层逻辑块执行完毕，执行预留操作
                localContext.getContexts().forEach(context -> {
                    try{
                        context.getExpression().evaluate(context.getExecution(), context.getParams());
                    }catch (Throwable e) {
                        LOGGER.error("执行预留操作出错" , e);
                    }
                });

                boolean flag = localContext.isErrorOccurs();

                resetReservedContext();

                //如果有异常，抛出异常
                if (flag) {
                    endLog(themisTestExecution, beginTime);

                    throw new LibraExpressionException("执行logic文件出错" , localContext.getThrowable());
                }
            }else{
                //下层逻辑执行完毕，返回上层
                localContext.setDepth(localContext.getDepth() - 1);
            }
        }
        
        endLog(themisTestExecution, beginTime);
    }



    private void beginLog(ThemisTestExecution themisTestExecution){
        LOGGER.debug(String.format("用例[%s]开始执行，作者[%s]，描述[%s]",
                themisTestExecution.getLibraData().getIndex(),
                themisTestExecution.getLibraData().getAuthor(),
                themisTestExecution.getLibraData().getDesc()));
    }

    private void endLog(ThemisTestExecution themisTestExecution, Date beginTime){
        LOGGER.debug(String.format("用例[%s]执行完成，耗时[%s]ms",
                themisTestExecution.getLibraData().getIndex(),
                Duration.between(beginTime.toInstant(), new Date().toInstant()).toMillis()));
    }
}
