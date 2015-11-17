package me.j360.application.spring.logger;

import me.j360.jdk.application.logger.BizLogger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created with j360-jdk -> me.j360.application.spring.logger.
 * User: min_xu
 * Date: 2015/11/17
 * Time: 16:31
 * 说明：
 */
public class BizLoggerFactoryBean implements FactoryBean<BizLogger>,
        InitializingBean, DisposableBean {

        public BizLogger getBizLogger() {
                return bizLogger;
        }

        public void setBizLogger(BizLogger bizLogger) {
                this.bizLogger = bizLogger;
        }

        private BizLogger bizLogger;

        @Override
        public void destroy() throws Exception {

        }

        @Override
        public BizLogger getObject() throws Exception {
                return bizLogger;
        }

        @Override
        public Class<?> getObjectType() {
                return bizLogger.getClass();
        }

        @Override
        public boolean isSingleton() {
                return false;
        }

        @Override
        public void afterPropertiesSet() throws Exception {

        }
}
