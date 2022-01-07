package cn.waifutong.experimentData.controller;

import cn.waifutong.experimentData.service.DataAllService;
import cn.waifutong.experimentData.util.FileUtils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;


@RestController
public class FileController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private DataAllService dataAllService;
    @RequestMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            // 获取大小
            long size = file.getSize();
            log.info("文件大小： " + size);
            // 判断上传文件大小
            if (!FileUtils.checkFileSize(file,50,"M")) {
                log.error("上传文件规定小于50MB");
                return "上传文件规定小于50MB";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            String filePath = "D:/demoupload/";
            String path = filePath + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @PostMapping("/batch")
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            String filePath = "C:/software/file/";
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + file.getOriginalFilename())));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "第 " + i + " 个文件上传失败 ==> "
                            + e.getMessage();
                }
            } else {
                return "第 " + i
                        + " 个文件上传失败因为文件为空";
            }
        }
        return "上传成功";
    }
    /**
     * zip文件上传并解压
     */
    @PostMapping("/upload/zip")
    public String uploadZipFile(@RequestParam("zipFile") MultipartFile zipFile) throws IOException, ZipException {
        String zipFileName = zipFile.getOriginalFilename();
        String path = "D:\\demoupload";
        File file = new File(path+"\\"+zipFileName);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        zipFile.transferTo(file);
        ZipFile zipFile1 = new ZipFile(file);
        zipFile1.setFileNameCharset("UTF-8");
        String dest = "D:\\demoupload\\extract";
        File destDir = new File(dest);
        zipFile1.extractAll(path);
        return "上传成功/r/n"+"文件名称："+zipFileName+"/r/n"+"路径："+path+zipFileName;
    }

    @PostMapping("/upload/zips")
    public String excelImport(@RequestParam("file") MultipartFile zipFile) throws IOException {
        String zipFileName = zipFile.getOriginalFilename();
        dataAllService.excelImport(zipFile);
        return null;
    }



}
