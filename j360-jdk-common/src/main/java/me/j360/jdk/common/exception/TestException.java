package me.j360.jdk.common.exception;

/**
 * Created with j360-jdk -> me.j360.jdk.common.exception.
 * User: min_xu
 * Date: 2015/10/21
 * Time: 10:48
 * 说明：
 * 你的答案是什么？是下面的答案吗？
 i=2
 i=1
 testEx2, catch exception
 testEx2, finally; return value=false
 testEx1, catch exception
 testEx1, finally; return value=false
 testEx, catch exception
 testEx, finally; return value=false
 如果你的答案真的如上面所说，那么你错啦。^_^，那就建议你仔细看一看这篇文章或者拿上面的代码按各种不同的情况修改、执行、测试，你会发现有很多事情不是原来想象中的那么简单的。现在公布正确答案：
 i=2
 i=1
 testEx2, catch exception
 testEx2, finally; return value=false
 testEx1, finally; return value=false
 testEx, finally; return value=false

 注意说明：

 finally语句块不应该出现 应该出现return。上面的return ret最好是其他语句来处理相关逻辑。
 */
public class TestException {
    public TestException() {
    }

    boolean testEx() throws Exception {
        boolean ret = true;
        try {
            ret = testEx1();
        } catch (Exception e) {
            System.out.println("testEx, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx, finally; return value=" + ret);
            return ret;
        }
    }

    boolean testEx1() throws Exception {
        boolean ret = true;
        try {
            ret = testEx2();
            if (!ret) {
                return false;
            }
            System.out.println("testEx1, at the end of try");
            return ret;
        } catch (Exception e) {
            System.out.println("testEx1, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx1, finally; return value=" + ret);
            return ret;
        }
    }

    boolean testEx2() throws Exception {
        boolean ret = true;
        try {
            int b = 12;
            int c;
            for (int i = 2; i >= -2; i--) {
                c = b / i;
                System.out.println("i=" + i);
            }
            return true;
        } catch (Exception e) {
            System.out.println("testEx2, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx2, finally; return value=" + ret);
            return ret;
        }
    }

    public static void main(String[] args) {
        TestException testException1 = new TestException();
        try {
            testException1.testEx();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
