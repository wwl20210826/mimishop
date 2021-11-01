package com.wwl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wwl.mapper.ProductInfoMapper;
import com.wwl.pojo.ProductInfo;
import com.wwl.pojo.ProductInfoExample;
import com.wwl.pojo.vo.ProductInfoVo;
import com.wwl.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductInfoServiceImpl")
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getAll() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {
        //分页插件使用PageHelper工具类完成分页设置(必须在取数据之前完成)
        PageHelper.startPage(pageNum,pageSize);

        //封装查询条件，添加按主键降序排序
        ProductInfoExample example = new ProductInfoExample();
        example.setOrderByClause("p_id desc");

        //将查询结果封装到PageInfo对象中
        List<ProductInfo> list = productInfoMapper.selectByExample(example);
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int save(ProductInfo info) {
        int num = productInfoMapper.insert(info);
        return num;
    }

    @Override
    public int delete(int pid) {
        return productInfoMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return productInfoMapper.deleteBatch(ids);
    }

    @Override
    public ProductInfo selectById(int id) {
        /*ProductInfoExample example = new ProductInfoExample();
        example.createCriteria().andPIdEqualTo(id);
        List<ProductInfo> list = productInfoMapper.selectByExample(example);
        if(list.size()==1){
            return list.get(0);
        }*/
        ProductInfo info = productInfoMapper.selectByPrimaryKey(id);
        return info;
    }

    @Override
    public int update(ProductInfo info) {
        return  productInfoMapper.updateByPrimaryKey(info);
    }

    @Override
    public PageInfo selectCondition(ProductInfoVo vo,int pageSize) {
        PageHelper.startPage(vo.getPage(),pageSize);
        List<ProductInfo> list = productInfoMapper.selectCondition(vo);
        PageInfo info = new PageInfo(list);
        return info;
    }

}

