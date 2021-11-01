package com.wwl.service.impl;

import com.wwl.mapper.AdminMapper;
import com.wwl.pojo.Admin;
import com.wwl.pojo.AdminExample;
import com.wwl.service.AdminService;
import com.wwl.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    //dao对象
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String name,String pwd) {
        //将条件封装
        AdminExample example = new AdminExample();
        example.createCriteria().andANameEqualTo(name);
        //获取结果
        List<Admin> list = adminMapper.selectByExample(example);
        //判断是否有该账号,密码是否正确
        if(list.size()==1){
            Admin admin = list.get(0);
            String mipwd = MD5Util.getMD5(pwd);
            if(admin.getaPass().equals(mipwd)){
                return admin;
            }
        }
        return null;
    }
}
