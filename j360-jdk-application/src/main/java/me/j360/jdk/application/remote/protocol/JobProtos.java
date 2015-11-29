package me.j360.jdk.application.remote.protocol;

/**
 * @author Robert HG (254963746@qq.com) on 7/23/14.
 *         ���ڶ���ͨ��Э���е�һЩcode
 */
public class JobProtos {

    private JobProtos() {
    }

    public enum RequestCode {
        // ����
        HEART_BEAT(10),
        // �ύ����
        SUBMIT_JOB(11),
        // ѯ�� ����ִ�е����
        ASK_JOB_PROGRESS(12),
        // �ַ�����
        PUSH_JOB(13),
        // ����ִ�����
        JOB_FINISHED(14),
        // ѯ������
        JOB_ASK(15),
        // ������������
        JOB_PULL(16),
        // TaskTracker��ҵ����־
        BIZ_LOG_SEND(17),
        // ȡ��(ɾ��)����
        CANCEL_JOB(18),;

        private int code;

        RequestCode(int code) {
            this.code = code;
        }

        public static RequestCode valueOf(int code) {
            for (RequestCode requestCode : RequestCode.values()) {
                if (requestCode.code == code) {
                    return requestCode;
                }
            }
            throw new IllegalArgumentException("can't find the request code !");
        }

        public int code() {
            return this.code;
        }
    }

    public enum ResponseCode {
        // ����ִ����
        JOB_IN_PROGRESS(10),
        // ��������ɹ�
        JOB_RECEIVE_SUCCESS(11),
        // ��������ʧ��
        JOB_RECEIVE_FAILED(12),
        // ����ִ�д���
        JOB_RUN_ERROR(12),
        // ����ִ��ʧ��
        JOB_RUN_FAILURE(13),
        // û������ڵ�ִ��
        NO_TASK_TRACKER(15),
        // ����ɹ�
        HEART_BEAT_SUCCESS(16),
        // û�нڵ����
        NO_NODE_GROUP(17),
        // û�п��õ� ����ִ��
        NO_AVAILABLE_JOB_RUNNER(18),
        // �������ͳɹ�
        JOB_PUSH_SUCCESS(19),
        // ������ɹ�
        JOB_NOTIFY_SUCCESS(20),
        // ��������
        JOB_PULL_SUCCESS(21),
        // ҵ����־���ͳɹ�
        BIZ_LOG_SEND_SUCCESS(22),
        // ����ɾ��ɹ�
        JOB_CANCEL_SUCCESS(23),
        // ����ɾ��ʧ��
        JOB_CANCEL_FAILED(24);


        private int code;

        ResponseCode(int code) {
            this.code = code;
        }

        public static ResponseCode valueOf(int code) {
            for (ResponseCode responseCode : ResponseCode.values()) {
                if (responseCode.code == code) {
                    return responseCode;
                }
            }
            throw new IllegalArgumentException("can't find the response code !");
        }

        public int code() {
            return this.code;
        }

    }
}
