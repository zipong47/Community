package zhipong.community.mapper;

import zhipong.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment record);
}