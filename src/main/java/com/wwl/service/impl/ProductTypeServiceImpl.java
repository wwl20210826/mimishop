package com.wwl.service.impl;

import com.wwl.mapper.ProductTypeMapper;
import com.wwl.pojo.ProductType;
import com.wwl.pojo.ProductTypeExample;
import com.wwl.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getAll() {
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
