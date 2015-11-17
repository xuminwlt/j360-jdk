package me.j360.jdk.application.logger;

/**
 * Created with j360-jdk -> me.j360.jdk.application.logger.
 * User: min_xu
 * Date: 2015/11/17
 * Time: 15:42
 * 说明：
 */
public enum LogType {
    RECEIVE,         // 接受任务
    SENT,            // 任务发送 开始执行
    FINISHED,        // 任务执行完成
    RESEND,          //  重新发送的任务执行结果
    FIXED_DEAD,       // 修复死掉的任务
    BIZ,             // 业务日志
    DEL             // 删除
    ;
}
