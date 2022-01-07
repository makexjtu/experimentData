package cn.waifutong.experimentData.service.impl;

import cn.waifutong.experimentData.entity.DataAll;
import cn.waifutong.experimentData.service.DownloadService;
import cn.waifutong.experimentData.service.TestService;
import cn.waifutong.experimentData.util.MyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TestServiceImpl implements TestService {


    @Override
    public List<DataAll> readExcel(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            log.error("上传文件格式不正确");
        }
        Workbook workbook = null;
        //生成字节流
        InputStream is = file.getInputStream();
        //判断版本
        if (fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(is);
        }
        if (fileName.endsWith("xls")) {
            workbook = new HSSFWorkbook(is);
        }

        List<DataAll> list = new ArrayList<>();
        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);

            //getLastRowNum()获取最后一行
            int lastrow = sheet.getLastRowNum();
            for (int i = 3; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
//                if(row.getPhysicalNumberOfCells()!=0){
                DataAll d = new DataAll();
                d.setExp_type (getCellStr(row,1));
                d.setTechnique (getCellStr(row,2));
                d.setNum_cycle (getCellStr(row,3));

                d.setTemp1 (getCellStr(row,4));
                d.setTime1 (getCellStr(row,5));
                d.setIrate1 (getCellStr(row,6));
                d.setDrate1 (getCellStr(row,7));
                d.setSigma1 (getCellStr(row,8));
                d.setSigma0p21 (getCellStr(row,9));
                d.setDelta1 (getCellStr(row,10));
                d.setPsai1 (getCellStr(row,11));
                d.setTau1 (getCellStr(row,12));

                d.setTemp2 (getCellStr(row,13));
                d.setTime2 (getCellStr(row,14));
                d.setIrate2 (getCellStr(row,15));
                d.setDrate2 (getCellStr(row,16));
                d.setSigma2 (getCellStr(row,17));
                d.setSigma0p22 (getCellStr(row,18));
                d.setDelta2 (getCellStr(row,19));
                d.setPsai2 (getCellStr(row,20));
                d.setTau2 (getCellStr(row,21));

                d.setTemp3 (getCellStr(row,22));
                d.setTime3 (getCellStr(row,23));
                d.setIrate3 (getCellStr(row,24));
                d.setDrate3 (getCellStr(row,25));
                d.setSigma3 (getCellStr(row,26));
                d.setSigma0p23 (getCellStr(row,27));
                d.setDelta3 (getCellStr(row,28));
                d.setPsai3 (getCellStr(row,29));
                d.setTau3 (getCellStr(row,30));

                d.setCti (getCellStr(row,31));
                d.setCal (getCellStr(row,32));
                d.setCv (getCellStr(row,33));
                d.setCfe (getCellStr(row,34));
                d.setCc (getCellStr(row,35));
                d.setCh (getCellStr(row,36));
                d.setCo (getCellStr(row,37));
                d.setGuige (getCellStr(row,38));

                d.setBeta_temp (getCellStr(row,39));
                d.setPhase (getCellStr(row,40));
                d.setPici (getCellStr(row,41));
                d.setPihao (getCellStr(row,42));
                d.setJiequxuhao (getCellStr(row,43));
                d.setDiameter_min (getCellStr(row,44));
                d.setDiameter_max (getCellStr(row,45));

                list.add(d);
//                }
            }
        }
        return list;
    }

    @Override
    public void addTestAll(Long userId) {

    }

    private String getCellStr (Row row, int rowNum){
        String r = null;
//        if (row.getCell(rowNum) != null){
        Cell cell = row.getCell(rowNum);
        DataFormatter formatter = new DataFormatter();
        r = formatter.formatCellValue(cell);
//        }
        return r;
    }
}
