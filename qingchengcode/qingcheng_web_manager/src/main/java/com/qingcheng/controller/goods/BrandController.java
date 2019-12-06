package com.qingcheng.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;


import com.qingcheng.entity.PageResult;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference   //跨域注入
    private BrandService brandService;

    /*
    查询所有
     */
    @RequestMapping("/findAll")
    public List<Brand> findAll(){
        return brandService.findAll();
    }

    /*
    分页查询品牌
     */
    @GetMapping("/findPage")
    public PageResult<Brand> findPage(int page,int size){
        return  brandService.findPage(page,size);
    }

    /*
    条件查询  在service层中创建通用mapper中的一个Example对象去封装查询条件
     然后调用dao层接口把这个example对象传进去执行条件查询操作： brandMapper.selectByExample(example);
     */
    @PostMapping("/findList")
    public List<Brand> findList( @RequestBody Map searchMap){
        return brandService.findList(searchMap);
    }

    /*
    条件分页查询
     */
    @PostMapping("/findPage")
    public PageResult<Brand>  findPage(@RequestBody Map searchMap,int page,int size ){
        return brandService.findPage(searchMap,page,size);
    }

    /*
    根据id查询
     */
    @GetMapping("/findById")
    public Brand findById(Integer id){
        return brandService.findById(id);
    }

    /*
    新增品牌
     */
    @PostMapping("/add")
    public Result add(@RequestBody  Brand brand){
        brandService.add(brand);
        return new Result();
    }

    /*
    修改品牌
     */
    @PostMapping("/update")
    public Result update(@RequestBody  Brand brand){
        brandService.update(brand);
        return new Result();
    }

    /*
    删除品牌
     */
    @GetMapping("delete")
    public Result delete(Integer id){
        brandService.delete(id);
        return new Result();
    }

}
