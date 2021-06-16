package zhipong.community.cache;

import org.apache.commons.lang3.StringUtils;
import zhipong.community.dto.TagDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author zhipong
 * @Date 2021/6/16
 */
public class TagCache {
    public static List<TagDTO> get() {
        ArrayList<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO programe = new TagDTO();
        programe.setCategoryName("开发语言");
        programe.setTags(Arrays.asList("js", "php", "java", "pyth on","c++","ruby"));
        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("spring","spring boot"));
        tagDTOS.add(programe);
        tagDTOS.add(framework);
        return tagDTOS;
    }

    public static String filterIsValid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();
        List<String> tagList = tagDTOS.stream().flatMap(tag -> (tag.getTags().stream())).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
