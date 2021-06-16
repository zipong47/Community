package zhipong.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author zhipong
 * @Date 2021/6/16
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
