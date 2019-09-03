package com.itheima.dao;

import com.itheima.domain.Account;
import com.itheima.domain.AccountUser;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有用户
     *
     */
    List<Account> findAll();

    List<AccountUser> findAccountUser();
}
