package me.j360.jdk.application.logger;

import java.util.List;

/**
 * Created with j360-jdk -> me.j360.jdk.application.logger.
 * User: min_xu
 * Date: 2015/11/17
 * Time: 15:43
 * 说明：
 */
public interface BizLogger {
    public void log(BizLogPo bizLogPo);

    public void log(List<BizLogPo> bizLogPos);
}
