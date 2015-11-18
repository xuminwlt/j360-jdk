package me.j360.application.spring.test.logger;

import me.j360.application.spring.logger.BizLoggerFactoryBean;
import me.j360.jdk.application.logger.BizLogger;
import me.j360.jdk.application.logger.BizLoggerFactory;
import me.j360.jdk.application.logger.console.ConsoleBizLogger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with j360-jdk -> me.j360.application.spring.test.
 * User: min_xu
 * Date: 2015/11/17
 * Time: 16:55
 * 说明：
 */

@Configuration
public class LoggerSpringConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean(name="bizLogger")
    public BizLogger getBizLogger() throws Exception {
        BizLoggerFactoryBean bizLoggerFactoryBean = new BizLoggerFactoryBean();
        bizLoggerFactoryBean.setBizLogger(new ConsoleBizLogger());
        return bizLoggerFactoryBean.getObject();
    }

}
