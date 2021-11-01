package com.wwl.service;

import com.github.pagehelper.PageInfo;
import com.wwl.pojo.ProductInfo;
import com.wwl.pojo.vo.ProductInfoVo;

import java.util.List;

public interface ProductInfoService {
    //查询所有商品不分页
    List<ProductInfo> getAll();

    //分页功能实现
    PageInfo splitPage(int pageNum,int pageSize);

    //添加商品
    int save(ProductInfo info);

    //单一删除
    int delete(int pid);

    //多选删除
    int deleteBatch(String[] ids);

    //查询商品通过id
    ProductInfo selectById(int id);

    //更新商品
    int update(ProductInfo info);

    //多条件查询
    PageInfo<ProductInfo> selectCondition(ProductInfoVo vo,int pageSize);

}
