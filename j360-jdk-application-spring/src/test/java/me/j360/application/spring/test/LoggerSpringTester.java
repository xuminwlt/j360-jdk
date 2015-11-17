package me.j360.application.spring.test;

import me.j360.jdk.application.logger.BizLogPo;
import me.j360.jdk.application.logger.BizLogger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with j360-jdk -> me.j360.application.spring.test.
 * User: min_xu
 * Date: 2015/11/17
 * Time: 17:01
 * 说明：
 */
public class LoggerSpringTester {

    @Test
    public void loggerSpringTest(){
        ApplicationContext context = new AnnotationConfigApplicationContext(LoggerSpringConfig.class);
        BizLogger bizLogger = (BizLogger) context.getBean("bizLogger");
        List<BizLogPo> list = new ArrayList<BizLogPo>();
        for(int i =0;i<=10;i++){
            BizLogPo jobLogPo = new BizLogPo();
            jobLogPo.setMsg("hello" + i);
            list.add(jobLogPo);
        }
        bizLogger.log(list);
    }



}
