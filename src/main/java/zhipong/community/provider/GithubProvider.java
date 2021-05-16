package zhipong.community.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import zhipong.community.dto.AccessTokenDTO;
import zhipong.community.dto.GithubUser;

import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhipong
 * @Date 2021/5/11
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));

        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String[] split = string.split("&");
            String token = split[0].split("=")[1];
            return token;
        } catch (IOException e) {
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
//        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(60000, TimeUnit.MILLISECONDS)
//                .readTimeout(60000, TimeUnit.MILLISECONDS)
//                .build();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
//                .url("https://api.github.com/user?access_token=" + accessToken)
                .addHeader("Authorization", " token "+accessToken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }

}
