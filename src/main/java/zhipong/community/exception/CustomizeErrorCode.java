package zhipong.community.exception;

/**
 * @Author zhipong
 * @Date 2021/6/10
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"问题不存在！"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复！"),
    NO_LOGIN(2003,"不能进行评论，请先登录"),
    SYSTEM_ERROR(2004,"服务器出错了"),
    TYPE_PARAM_ERROR(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"你评论的评论不存在"),
    CONTENT_IS_EMPTY(2007,"输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2008,"你只是在读别人的信息呢"),
    NOTIFICATION_NOT_FOUND(2009,"通知信息未找到"),
    FILE_UPLOAD_ERROR(2010,"图片上传失败");
    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public Integer getCode(){
        return code;
    }
}
