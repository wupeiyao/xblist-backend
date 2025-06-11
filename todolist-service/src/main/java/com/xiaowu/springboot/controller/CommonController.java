package com.xiaowu.springboot.controller;

import com.xiaowu.springboot.constant.ApiConstant;
import com.xiaowu.springboot.constant.MessageConstant;
import com.xiaowu.springboot.result.Result;
import com.xiaowu.springboot.utils.AliOssUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/10/10 12:21
 */
@RestController
@Tag(name = "通用接口")
@Slf4j
@RequestMapping("/user")
public class CommonController {


    @Resource
    private AliOssUtil aliOssUtil;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam(value = "avatar") MultipartFile file) {
        log.info("文件上传：{}", file);

        try {
            // 原始文件名
            String originalFilename = file.getOriginalFilename();
            // 截取原始文件名的后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 构造新文件名称
            String objectName = UUID.randomUUID().toString() + extension;

            // 文件的请求路径
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            System.out.println(filePath);
            log.info("{}",filePath);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);

    }

    // @Value("${uploadPathImg}")
    // private String uploadPathImg;
    // @ApiOperation("文件上传")
    // @PostMapping("/upload")
    // public Result<String> upload(@RequestParam(value = "avatar")MultipartFile file) throws IOException {
    //     String fileName = System.currentTimeMillis() + file.getOriginalFilename();
    //     try {
    //         if (file != null) {
    //             String upload_file_dir=uploadPathImg;//注意这里需要添加目录信息
    //             String destFileName =  uploadPathImg +fileName;
    //             //4.第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录（创建到了webapp下uploaded文件夹下）
    //             File upload_file_dir_file = new File(upload_file_dir);
    //             if (!upload_file_dir_file.exists())
    //             {
    //                 upload_file_dir_file.mkdirs();
    //             }
    //             //5.把浏览器上传的文件复制到希望的位置
    //             File targetFile = new File(upload_file_dir_file, fileName);
    //             file.transferTo(targetFile);
    //         }
    //     }catch (Exception e){
    //         e.printStackTrace();
    //     }
    //     return Result.success(fileName);
    // }
    //
    // @PostConstruct
    // public void checkUploadPath() {
    //     System.out.println("Upload path loaded: " + uploadPathImg);
    // }


    // @PostMapping("/upload")
    // public Result<String> upload(@RequestParam("avatar") MultipartFile file) {
    //     log.info("文件上传: {}", file);
    //
    //     if (file.isEmpty()) {
    //         log.error("上传的文件为空");
    //         return Result.error("文件不能为空");
    //     }
    //
    //     // 获取原始文件名并检查合法性
    //     String originalFilename = file.getOriginalFilename();
    //     if (originalFilename == null || !originalFilename.contains(".")) {
    //         log.error("无效的文件名: {}", originalFilename);
    //         return Result.error("文件名不合法");
    //     }
    //
    //     // 获取文件后缀
    //     String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
    //     log.info("上传文件的后缀: {}", suffix);
    //
    //     // 确保存储目录存在
    //     File dir = new File(basePath);
    //     if (!dir.exists() && !dir.mkdirs()) {
    //         log.error("无法创建存储目录: {}", basePath);
    //         return Result.error("无法创建存储目录");
    //     }
    //
    //     // 生成新的文件名，防止重复
    //     String fileName = UUID.randomUUID().toString() + suffix;
    //     File destFile = new File(basePath + fileName);
    //
    //     try {
    //         // 保存文件到指定路径
    //         file.transferTo(destFile);
    //         log.info("文件成功上传: {}", destFile.getPath());
    //     } catch (IOException e) {
    //         log.error("文件上传失败", e);
    //         return Result.error("文件上传失败，请稍后重试");
    //     }
    //
    //     // 返回文件的相对路径
    //     String relativePath = "/uploads/" + fileName;
    //     log.info("文件的相对路径: {}", relativePath);
    //
    //
    //     return Result.success(relativePath);
    // }

    // /**
    //  * @description:
    //  * @author: xiaowu
    //  * @time: 2024/5/9 21:15
    //  */
    // @GetMapping("/download")
    // public void download(String name, HttpServletResponse response) throws IOException {
    //     //输入流,通过输入流读取文件内容
    //     FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
    //
    //     //输入流,通过输出流将文件写回浏览器,在浏览器展示图片
    //     ServletOutputStream outputStream = response.getOutputStream();
    //
    //     response.setContentType("image/jpeg");
    //
    //     int length = 0;
    //     byte[] bytes = new byte[1024];
    //
    //     while ((length = fileInputStream.read(bytes)) != -1){
    //         outputStream.write(bytes,0,length);
    //         outputStream.flush();
    //     }
    //     outputStream.close();
    //     fileInputStream.close();
    // }
}
