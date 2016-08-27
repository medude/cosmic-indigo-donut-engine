package apis.errorHandle;

public class NullErrorHandler implements ErrorHandleType {
    @Override
    public void handle(Throwable throwable, String message, boolean doShowError) {
    }
}
