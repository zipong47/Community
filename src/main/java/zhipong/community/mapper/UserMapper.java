package zhipong.community.mapper;

import org.apache.ibatis.annotations.*;
import zhipong.community.model.User;

/**
 * @Author zhipong
 * @Date 2021/5/17
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modify,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer creator);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name=#{name}, token=#{token}, gmt_modify=#{gmtModified}, avatar_url=#{avatarUrl} where id=#{id}")
    void update(User user);
}
