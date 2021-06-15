package zhipong.community.dto;

import lombok.Data;
import zhipong.community.model.User;

/**
 * @Author zhipong
 * @Date 2021/6/13
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Integer commentCount;
    private String content;
    private Long commenter;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private User user;
}
