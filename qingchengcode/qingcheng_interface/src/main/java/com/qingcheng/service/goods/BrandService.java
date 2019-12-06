package com.qingcheng.service.goods;

import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Brand;
import java.util.List;
import java.util.Map;

/*
相当于品牌的service层的接口层
 */
public interface BrandService {

    public List<Brand> findAll();

    public PageResult<Brand> findPage(int page,int size);   //分页查询

    public List<Brand> findList(Map<String,Object> searchMap);  //条件查询

    /*
    条件分页查询
     */
    public PageResult<Brand> findPage(Map<String,Object> searchMap,int page,int size);

    /*
    根据id查询
     */
    public Brand findById(Integer id);

    /*
    新增品牌
     */
    public void add(Brand brand);

    /*
    修改品牌
     */
    public void update(Brand brand);

    /*
    删除品牌
     */
    public void delete(Integer id);
}
