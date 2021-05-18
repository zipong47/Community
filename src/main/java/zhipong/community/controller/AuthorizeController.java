package zhipong.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zhipong.community.dto.AccessTokenDTO;
import zhipong.community.dto.GithubUser;
import zhipong.community.mapper.UserMapper;
import zhipong.community.model.User;
import zhipong.community.provider.GithubProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @Author zhipong
 * @Date 2021/5/11
 */
@Controller
public class AuthorizeController {
    // 把我容器里的写好的实例化的一个实例，加载到当前使用的上下文
    @Autowired
    private GithubProvider githubProvider;

    //启动文件时，读取配置，将配置放到Spring的Map里
    @Value("${github.client.id}")
    private String clientID;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Value("${gitee.grant.type}")
    private String grantType;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code") String code,
                           HttpServletRequest request) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setGrant_type(grantType);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientID);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_secret(clientSecret);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser((accessToken));
        if(user!=null){
            User insertUser = new User();
            insertUser.setToken(UUID.randomUUID().toString());
            insertUser.setName(user.getName());
            insertUser.setAccountId(String.valueOf(user.getId()));
            insertUser.setGmtCreate(System.currentTimeMillis());
            insertUser.setGmtModified(insertUser.getGmtCreate());
            userMapper.insert(insertUser);
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }
}
