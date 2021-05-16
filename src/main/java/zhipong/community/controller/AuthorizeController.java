package zhipong.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zhipong.community.dto.AccessTokenDTO;
import zhipong.community.dto.GithubUser;
import zhipong.community.provider.GithubProvider;

/**
 * @Author zhipong
 * @Date 2021/5/11
 */
@Controller
public class AuthorizeController {
    // 把我容器里的写好的实例化的一个实例，加载到当前使用的上下文
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name="code")String code,
                           @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("32b2e2cb1491594c797d");
        accessTokenDTO.setClient_secret("8520735901a688a5e086ee050fb8b9724406b134");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser((accessToken));
        System.out.println(user.getName());
        return "index";
    }
}
