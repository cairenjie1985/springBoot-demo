package me.caixin.service;

import me.caixin.dao.AccountMapper;
import me.caixin.dto.AccountQueryDTO;
import me.caixin.entity.AccountEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by roy on 2017/9/3.
 */
@Service("accountService")
public class AccountService {

    @Resource
    AccountMapper accountMapper;


    public String getNameById(int id){
        String name =null ;
      AccountEntity accountEntity = accountMapper.getOne(id);
      if(null!=accountEntity){
          name = accountEntity.getUserName();
      }
      return name;
    }

    public List<AccountEntity> selectAccountByName(AccountQueryDTO accountQueryDTO){
        return accountMapper.selectAccountByName(accountQueryDTO);
    }

    public AccountEntity getAccount(int id){
        return accountMapper.getOne(id);
    }

}
