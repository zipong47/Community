package zhipong.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zhipong.community.dto.CommentDTO;
import zhipong.community.dto.ResultDTO;
import zhipong.community.exception.CustomizeErrorCode;
import zhipong.community.model.Comment;
import zhipong.community.model.User;
import zhipong.community.service.CommentService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zhipong
 * @Date 2021/6/11
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommenter(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.OkOf();
    }
}
