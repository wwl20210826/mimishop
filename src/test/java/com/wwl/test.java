package com.wwl;

import com.wwl.mapper.ProductInfoMapper;
import com.wwl.pojo.ProductInfo;
import com.wwl.pojo.vo.ProductInfoVo;
import com.wwl.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext_dao.xml","classpath:applicationContext_service.xml"})
public class test {

    @Autowired
    ProductInfoMapper productInfoMapper;
    @Test
    public void test01(){
        String mi = "";
        mi = MD5Util.getMD5("000000");
        System.out.println(mi);
    }
    @Test
    public void test02(){
        ProductInfoVo vo = new ProductInfoVo();
        //vo.setPName("4");
        /*vo.setlPrice(3000);
        vo.sethPrice(4500);*/
        List<ProductInfo> list =productInfoMapper.selectCondition(vo);
        for(ProductInfo info:list ){
            System.out.println(info);
        }
    }
}
