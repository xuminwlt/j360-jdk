package me.j360.jdk.application.test.logger;

import me.j360.jdk.application.common.support.Config;
import me.j360.jdk.application.logger.BizLogPo;
import me.j360.jdk.application.logger.BizLoggerDelegate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with j360-jdk -> me.j360.jdk.application.test.logger.
 * User: min_xu
 * Date: 2015/11/17
 * Time: 16:27
 * 说明：
 */
public class LoggerTest {

    @Test
    public void loggerTest() throws InterruptedException {
        Config config = new Config();
        config.setParameter("biz.logger","console");

        List<BizLogPo> list = new ArrayList<BizLogPo>();
        for(int i =0;i<=10;i++){
            BizLogPo jobLogPo = new BizLogPo();
            jobLogPo.setMsg("hello" + i);
            list.add(jobLogPo);
        }
        TimeUnit.SECONDS.sleep(5);
        BizLoggerDelegate jobLoggerDelegate = new BizLoggerDelegate(config);
        jobLoggerDelegate.log(list);
    }
}
