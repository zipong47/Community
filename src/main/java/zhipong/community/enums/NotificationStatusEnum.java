package zhipong.community.enums;

/**
 * @Author zhipong
 * @Date 2021/6/18
 */
public enum NotificationStatusEnum {
    UNREAD(0),
    READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
