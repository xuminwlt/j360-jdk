package me.j360.jdk.application.logger;

/**
 * Created with j360-jdk -> me.j360.jdk.application.logger.
 * User: min_xu
 * Date: 2015/11/17
 * Time: 15:44
 * 说明：
 */
public class BizLogException extends RuntimeException {

    public BizLogException() {
        super();
    }

    public BizLogException(String message) {
        super(message);
    }

    public BizLogException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizLogException(Throwable cause) {
        super(cause);
    }

    protected BizLogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
