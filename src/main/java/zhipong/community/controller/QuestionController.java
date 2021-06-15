package zhipong.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zhipong.community.dto.CommentCreateDTO;
import zhipong.community.dto.CommentDTO;
import zhipong.community.dto.QuestionDTO;
import zhipong.community.enums.CommentTypeEnum;
import zhipong.community.service.CommentService;
import zhipong.community.service.QuestionService;

import java.util.List;

/**
 * @Author zhipong
 * @Date 2021/6/4
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        List<CommentDTO> comments = commentService.listByQuestionId(id, CommentTypeEnum.QUESTION);
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        return "question";
    }
}
