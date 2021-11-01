package com.wwl.service;

import com.wwl.pojo.Admin;

public interface AdminService {
    //登录验证
    Admin login(String name,String pwd);
}
