package zhipong.community.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import zhipong.community.model.Question;
import zhipong.community.model.QuestionExample;

import java.util.List;

public interface QuestionExtMapper {
    int incView (Question record);
}