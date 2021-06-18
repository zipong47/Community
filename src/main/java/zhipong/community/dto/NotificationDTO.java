package zhipong.community.dto;

import lombok.Data;

/**
 * @Author zhipong
 * @Date 2021/6/18
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerid;
    private String typeName;
    private Integer type;
}
