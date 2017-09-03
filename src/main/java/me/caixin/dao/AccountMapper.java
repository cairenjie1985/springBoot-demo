package me.caixin.dao;

import me.caixin.entity.AccountEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by roy on 2017/9/2.
 */
public interface AccountMapper {

    @Select("SELECT * FROM account")
    @Results({
            @Result(property = "userSex",  column = "user_sex"),
            @Result(property = "nickName", column = "nick_name")
    })
    List<AccountEntity> getAll();

    @Select("SELECT * FROM account WHERE id = #{id}")
    @Results({
            @Result(property = "userSex",  column = "user_sex"),
            @Result(property = "nickName", column = "nick_name")
    })
    AccountEntity getOne(Long id);

    @Insert("INSERT INTO account(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(AccountEntity user);

    @Update("UPDATE account SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(AccountEntity user);

    @Delete("DELETE FROM account WHERE id =#{id}")
    void delete(Long id);


    AccountEntity selectAccountByName(@Param("userName") String userName);
}
