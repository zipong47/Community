package zhipong.community.dto;

import lombok.Data;

/**
 * @Author zhipong
 * @Date 2021/6/22
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}
