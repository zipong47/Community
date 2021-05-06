package zhipong.community;

import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;

/**
 * @Author zhipong
 * @Description TODO
 * @Date 2021/5/6 12:54
 * @Version Maven 3.6.3
 */
public class getVersionTest {
    public void Test1(){
        String version = SpringVersion.getVersion();
        String version1 = SpringBootVersion.getVersion();
        System.out.println(version);
        System.out.println(version1);
    }

    public static void main(String[] args) {
        getVersionTest t=new getVersionTest();
        t.Test1();
    }
}