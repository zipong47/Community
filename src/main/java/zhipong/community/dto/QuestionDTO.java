package zhipong.community.dto;

import lombok.Data;
import zhipong.community.model.User;

/**
 * @Author zhipong
 * @Date 2021/5/24
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
