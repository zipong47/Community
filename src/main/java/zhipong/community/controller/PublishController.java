package zhipong.community.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zhipong.community.cache.TagCache;
import zhipong.community.dto.QuestionDTO;
import zhipong.community.mapper.QuestionMapper;
import zhipong.community.model.Question;
import zhipong.community.model.User;
import zhipong.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zhipong
 * @Date 2021/5/19
 */
@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id",question.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(Model model) {
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam(value = "id",required = false) Long id,
            HttpServletRequest request,
            Model model) {

        User user= (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "???????????????");
            return "publish";
        }
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", TagCache.get());
        if (title == null || title == "") {
            model.addAttribute("error", "??????????????????");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "????????????????????????");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "??????????????????");
            return "publish";
        }
        String invalid = TagCache.filterIsValid(tag);
        if(StringUtils.isNotBlank(invalid)){
            model.addAttribute("error","??????????????????: "+invalid);
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
