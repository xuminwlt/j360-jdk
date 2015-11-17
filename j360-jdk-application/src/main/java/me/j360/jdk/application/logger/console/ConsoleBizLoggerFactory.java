package me.j360.jdk.application.logger.console;

import me.j360.jdk.application.logger.BizLogPo;
import me.j360.jdk.application.logger.BizLogger;
import me.j360.jdk.application.logger.BizLoggerFactory;

import java.util.List;

/**
 * Created with j360-jdk -> me.j360.jdk.application.logger.console.
 * User: min_xu
 * Date: 2015/11/17
 * Time: 16:08
 * 说明：
 */
public class ConsoleBizLoggerFactory implements BizLoggerFactory {
    @Override
    public BizLogger getJobLogger() {
        return new ConsoleBizLogger();
    }
}
