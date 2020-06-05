package com.wcyv90.spring.opentracing.app.b.mapper;

import com.wcyv90.spring.opentracing.app.b.domain.MessageRecord;
import com.wcyv90.spring.opentracing.app.b.domain.MessageRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface MessageRecordMapper {
    @SelectProvider(type=MessageRecordSqlProvider.class, method="countByExample")
    long countByExample(MessageRecordExample example);

    @DeleteProvider(type=MessageRecordSqlProvider.class, method="deleteByExample")
    int deleteByExample(MessageRecordExample example);

    @Delete({
        "delete from message_record",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into message_record (id, message_key, ",
        "message_value, update_time)",
        "values (#{id,jdbcType=BIGINT}, #{messageKey,jdbcType=VARCHAR}, ",
        "#{messageValue,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(MessageRecord record);

    @InsertProvider(type=MessageRecordSqlProvider.class, method="insertSelective")
    int insertSelective(MessageRecord record);

    @SelectProvider(type=MessageRecordSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="message_key", property="messageKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="message_value", property="messageValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<MessageRecord> selectByExample(MessageRecordExample example);

    @Select({
        "select",
        "id, message_key, message_value, update_time",
        "from message_record",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="message_key", property="messageKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="message_value", property="messageValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    MessageRecord selectByPrimaryKey(Long id);

    @UpdateProvider(type=MessageRecordSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MessageRecord record, @Param("example") MessageRecordExample example);

    @UpdateProvider(type=MessageRecordSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MessageRecord record, @Param("example") MessageRecordExample example);

    @UpdateProvider(type=MessageRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MessageRecord record);

    @Update({
        "update message_record",
        "set message_key = #{messageKey,jdbcType=VARCHAR},",
          "message_value = #{messageValue,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MessageRecord record);
}