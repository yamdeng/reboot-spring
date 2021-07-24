package reboot.spring.bean.aop;

public class ExceptionBean {

    public void handleAopExampleException(boolean executeThrowException) {
        if(executeThrowException) {
            throw new AopExampleException("handleAopExampleException");
        }
    }

    public void handleCheckedException(boolean executeThrowException) throws Exception {
        if(executeThrowException) {
            throw new Exception("handleAopExampleException");
        }
    }

}
