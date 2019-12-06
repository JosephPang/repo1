package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qingcheng.dao.BrandMapper;
import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    /*
    利用分页查询插件分页查询
     */
    @Override
    public PageResult<Brand> findPage(int page, int size) {
        PageHelper.startPage(page,size);       //分页插件
        Page<Brand> pageResult=(Page<Brand>) brandMapper.selectAll();
        return new PageResult<>(pageResult.getTotal(),pageResult.getResult());
    }

    /*
    条件查询
     */
    @Override
    public List<Brand> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return brandMapper.selectByExample(example);
    }

    /*
    条件加分页查询
     */
    @Override
    public PageResult<Brand> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        /*
        example就是一个查询条件的封装对象
         */
        Example example = createExample(searchMap);
        Page<Brand> pageResult=(Page<Brand>) brandMapper.selectByExample(example);
        return new PageResult<>(pageResult.getTotal(),pageResult.getResult());
    }

    /*
    根据id查询
     */
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /*
    新增品牌
     */
    @Override
    public void add(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    /*
    修改品牌
     */
    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /*
    删除品牌
     */
    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /*
    条件查询抽取的公共代码
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria(); //创建要去执行sql操作的对象

        if(searchMap!=null){
            /*
            模糊查找
             */
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name")) ){
                criteria.andLike("name","%"+(String)searchMap.get("name")+"%");
            }

            /*
            精确查找
             */
            if(searchMap.get("letter")!=null && !"".equals(searchMap.get("letter")) ){
                criteria.andEqualTo("letter",(String)searchMap.get("letter"));
            }
        }
        return example;
    }


}
