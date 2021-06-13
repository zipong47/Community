package zhipong.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhipong.community.enums.CommentTypeEnum;
import zhipong.community.exception.CustomizeErrorCode;
import zhipong.community.exception.CustomizeException;
import zhipong.community.mapper.CommentMapper;
import zhipong.community.mapper.QuestionExtMapper;
import zhipong.community.mapper.QuestionMapper;
import zhipong.community.model.Comment;
import zhipong.community.model.Question;

/**
 * @Author zhipong
 * @Date 2021/6/11
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;
    public void insert(Comment comment) {
        if(comment.getParentId()==null||comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType()==null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_ERROR);
        }
        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }else{
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1)gi;
            questionExtMapper.incCommentCount(question);
        }
    }
}
