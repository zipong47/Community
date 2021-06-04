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
import zhipong.community.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author zhipong
 * @Date 2021/5/11
 */
@Controller
public class AuthorizeController {
    // 把我容器里的写好的实例化的一个实 例，加载到当前使用的上下文
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    //启动文件时，读取配置，将配置放到Spring的Map里
    @Value("${github.client.id}")
    private String clientID;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Value("${gitee.grant.type}")
    private String grantType;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code") String code,
                           HttpServletResponse response) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setGrant_type(grantType);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientID);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_secret(clientSecret);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser giteeUser = githubProvider.getUser((accessToken));
        if(giteeUser!=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(giteeUser.getName());
            user.setAccountId(String.valueOf(giteeUser.getId()));
            user.setAvatarUrl(giteeUser.getAvatar_url());
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
