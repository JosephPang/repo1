package com.qingcheng.controller.file;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @Author:JiashengPang
 * @Description:  文件上传
 * @Date: 22:22 2019/12/5
 */

@RestController
@RequestMapping("/upload")
public class uploadController {

    @Autowired
    private HttpServletRequest request;
    //接收的参数是springMvc提供的文件对象  包括文件的二进制内容  以及名称等信息  专门用来做上传
    //本地上传
    @PostMapping("native")
    public String nativeUpload(@RequestParam("file") MultipartFile file){

        //拿到webapp下的img文件夹的绝对目录
        String path =request.getSession().getServletContext().getRealPath("img");

        //得到目录+文件名
        String filePath=path+"/"+file.getOriginalFilename();
        File desFile = new File(filePath);

        //判断dofile的上级目录是否存在 ,不存在就创建
        if (!desFile.getParentFile().exists()){
            desFile.mkdirs();   //如果不存在就创建
        }

        try {
            file.transferTo(desFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "http://localhost:9101/img/"+file.getOriginalFilename();  //返回图片在服务器的地址
    }



    /*
    阿里云oss上传：
        首先在配置文件中把ossClient配置成spring的一个bean
        然后注入这个bean
        给这个bean对象一些指定的参数 完成文件的上传
     */

    @Autowired
    private OSSClient ossClient;

    @PostMapping("oss")
    public String ossUpload(@RequestParam("file") MultipartFile file){
        String bucketName = "qingchengjsp";   //空间名称
        String filename = file.getOriginalFilename();
        try {
            ossClient.putObject(bucketName,filename,file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "https://"+bucketName+".oss-cn-beijing.aliyuncs.com/"+filename;
    }

}