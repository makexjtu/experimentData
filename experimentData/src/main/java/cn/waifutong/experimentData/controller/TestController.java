package cn.waifutong.experimentData.controller;


import cn.waifutong.experimentData.entity.DataAll;
import cn.waifutong.experimentData.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/run")
    public Object run(MultipartFile file) throws IOException, InvalidFormatException {

        Long userId = new Long(1);

        //新建并持久化: 实验表
        testService.addTestAll(userId);
        //新建并持久化: 实验输入表(带着实验Id)

        //读取Excel数据
        List<DataAll> excelDatalist = testService.readExcel(file);
        //将读取到的数据持久化到DB(带着输入Id)

        //根据实验ID读取输入数据

        //将输入数据转为csv文件并存到本地

        //将zip存到本地

        //请求算法服务

        //读取csv文件

        // 新建数据输出表(带着输入Id)

        // 并将输出数据持久化(带着输出Id)

        //返回实验Id和实验结果




        return excelDatalist;
    }



}
