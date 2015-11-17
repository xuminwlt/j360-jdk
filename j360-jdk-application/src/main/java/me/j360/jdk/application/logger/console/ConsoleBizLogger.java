package me.j360.jdk.application.logger.console;

import me.j360.jdk.application.common.util.JSONUtils;
import me.j360.jdk.application.logger.BizLogPo;
import me.j360.jdk.application.logger.BizLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created with j360-jdk -> me.j360.jdk.application.logger.console.
 * User: min_xu
 * Date: 2015/11/17
 * Time: 16:08
 * 说明：
 */
public class ConsoleBizLogger implements BizLogger {

    private Logger LOGGER = LoggerFactory.getLogger(ConsoleBizLogger.class.getSimpleName());

    @Override
    public void log(BizLogPo jobLogPo) {
        LOGGER.info(JSONUtils.toJSONString(jobLogPo));
    }

    @Override
    public void log(List<BizLogPo> jobLogPos) {
        for (BizLogPo jobLogPo : jobLogPos) {
            log(jobLogPo);
        }
    }
}
