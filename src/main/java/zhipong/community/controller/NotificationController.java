package zhipong.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zhipong.community.dto.NotificationDTO;
import zhipong.community.dto.PaginationDTO;
import zhipong.community.enums.NotificationEnum;
import zhipong.community.model.User;
import zhipong.community.service.NotificationService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zhipong
 * @Date 2021/6/18
 */
@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "id") Long id) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id,user);
        if(NotificationEnum.REPLY_COMMENT.getType()==notificationDTO.getType() || NotificationEnum.REPLY_QUESTION.getType()==notificationDTO.getType()){
            return "redirect:/question/"+notificationDTO.getOuterid();
        }else{
            return "redirect:/";
        }
    }
}
