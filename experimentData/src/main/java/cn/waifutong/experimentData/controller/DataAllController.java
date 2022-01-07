package cn.waifutong.experimentData.controller;

import cn.waifutong.experimentData.entity.User;
import cn.waifutong.experimentData.service.DataAllService;
import cn.waifutong.experimentData.util.CsvExportUtil;
import cn.waifutong.experimentData.util.MyException;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@RestController
public class DataAllController {

    @Autowired
    private DataAllService dataAllService;


    /**
     * csv导出
     */
    @GetMapping("/c/virtualMachine/export")
    public void export(HttpServletResponse response, String name, String nodeName, String isUse) {
       // List<Map<String, Object>> dataList = virtualMachineService.getCloudPhoneDetails(name, nodeName, isUse);
        List<Map<String, Object>> userList = dataAllService.selectDataAll();
        /**
         * 构造导出数据结构
         */
        //temp1,time1,irate1,drate1,sigma1,sigma0p21,delta1,psai1,tau1,temp2,time2,irate2,drate2,sigma2,sigma0p22,delta2,psai2,tau2,temp3,time3,irate3,drate3,sigma3,sigma0p23,delta3,psai3,tau3
        //保温温度,保温时间,加热速率,冷却速率,抗拉强度σb,屈服极限σ0.2,伸长率δ,断面收缩率ψ,剪切强度τ,保温温度2,保温时间2,加热速率2,冷却速率2,抗拉强度σb2,屈服极限σ0.22,伸长率δ2,断面收缩率ψ2,剪切强度τ2,保温温度3,保温时间3,加热速率3,冷却速率3,抗拉强度σb3,屈服极限σ0.23,伸长率δ3,4,剪切强度τ3
        String titles = "temp1,time1,irate1,drate1,sigma1,sigma0p21,delta1,psai1,tau1,temp2,time2,irate2,drate2,sigma2,sigma0p22,delta2,psai2,tau2,temp3,time3,irate3,drate3,sigma3,sigma0p23,delta3,psai3,tau3,cti,cal,cv,cfe,cc,ch,co,guige"; // 设置表头
        // 设置每列字段
        String keys ="temp1,time1,irate1,drate1,sigma1,sigma0p21,delta1,psai1,tau1,temp2,time2,irate2,drate2,sigma2,sigma0p22,delta2,psai2,tau2,temp3,time3,irate3,drate3,sigma3,sigma0p23,delta3,psai3,tau3,cti,cal,cv,cfe,cc,ch,co,guige";
        // 设置导出文件前缀
        String fName = "user_";
        // 文件导出
        try {
            OutputStream os = response.getOutputStream();
            CsvExportUtil.responseSetProperties(fName, response);
            CsvExportUtil.doExport(userList, titles, keys, os);
            os.close();
            //log.info("导出云手机数据成功, name={},nodeName={},isUse={}", name, nodeName, isUse);
            System.err.println("成功");
        } catch (Exception e) {
           // log.error("导出云手机数据失败 ,name={},nodeName={},isUse={},error:{}", name, nodeName, isUse, e.getMessage());
            System.err.println("失败");
        }
    }

}
