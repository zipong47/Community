package zhipong.community.dto;

import lombok.Data;

/**
 * @Author zhipong
 * @Date 2021/5/11
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
