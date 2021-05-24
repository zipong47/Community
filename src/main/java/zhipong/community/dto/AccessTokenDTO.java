package zhipong.community.dto;

import lombok.Data;

/**
 * @Author zhipong
 * @Date 2021/5/11
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String grant_type;
  }
