package zhipong.community.model;

import lombok.Data;

/**
 * @Author zhipong
 * @Date 2021/5/18
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
