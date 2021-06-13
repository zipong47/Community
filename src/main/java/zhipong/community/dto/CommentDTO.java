package zhipong.community.dto;

import lombok.Data;

/**
 * @Author zhipong
 * @Date 2021/6/11
 */
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
