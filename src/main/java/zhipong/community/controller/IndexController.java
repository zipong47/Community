package zhipong.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zhipong.community.dto.PaginationDTO;
import zhipong.community.dto.QuestionDTO;
import zhipong.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "5") Integer size,
                        @RequestParam(value = "search", required = false) String search) {
        PaginationDTO<QuestionDTO> pagination = questionService.list(search, page, size);
        model.addAttribute("pagination", pagination);
        model.addAttribute("search", search);
        return "index";
    }
}
