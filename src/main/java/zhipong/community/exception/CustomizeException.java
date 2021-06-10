package zhipong.community.exception;

/**
 * @Author zhipong
 * @Date 2021/6/10
 */
public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message=errorCode.getMessage();
    }
    public CustomizeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
