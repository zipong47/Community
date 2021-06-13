package zhipong.community.exception;

/**
 * @Author zhipong
 * @Date 2021/6/10
 */
public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;
    public CustomizeException(ICustomizeErrorCode errorCode){
        this.code=errorCode.getCode();
        this.message=errorCode.getMessage();
    }
    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
