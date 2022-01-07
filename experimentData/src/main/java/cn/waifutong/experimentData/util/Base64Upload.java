package cn.waifutong.experimentData.util;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;


import org.apache.commons.io.FileUtils;
import org.springframework.util.Base64Utils;

public class Base64Upload {
    
    /**
     * 相对路径
     */
    private List<String> pathList = new ArrayList<String>();
    /**
     * 物理路径
     */
    private List<String> realPathList = new ArrayList<String>();
    
    public List<String> base64Upload(List<String> base64DataList) throws Exception{
        for (String base64Data : base64DataList) {
            String path = "";
            String realPath = "";
            try{              
                String dataPrix = "";
                String data = "";
                
                if(base64Data == null || "".equals(base64Data)){
                    throw new Exception("上传失败，上传图片数据为空");
                }else{
                    String [] d = base64Data.split("base64,");
                    if(d != null && d.length == 2){
                        dataPrix = d[0];
                        data = d[1];
                    }else{
                        throw new Exception("上传失败，数据不合法");
                    }
                }             
                String suffix = "";
                if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
                    suffix = ".jpg";
                } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
                    suffix = ".ico";
                } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
                    suffix = ".gif";
                } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
                    suffix = ".png";
                }else{
                    throw new Exception("上传图片格式不合法");
                }
                
                
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("globalSetting.properties");
                Properties p = new Properties();
                p.load(inputStream);
                SimpleDateFormat sdfDay = new SimpleDateFormat("yyyyMMdd");
                path = "/" + sdfDay.format(new Date()) + "/";
                
                Properties prop = System.getProperties();
                String os = prop.getProperty("os.name");
                
                if ("win".equals(os.substring(0, 3).toLowerCase())) {
                    realPath = p.getProperty("default.winBasePath") + "/" + path;
                } else {
                    realPath = p.getProperty("default.linuxBasePath") + "/" + path;
                }
                
                File folder = new File(realPath.replace("/", File.separator));
                if (!folder.exists() || !folder.isDirectory()) {
                    folder.mkdirs();
                }
                
                String fileName = UUID.randomUUID().toString() + suffix;                
                
                path += fileName;
                realPath = realPath.replace(File.separator, "/");
                
                try{
                    //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
                    byte[] bs = Base64Utils.decodeFromString(data);
                    //使用apache提供的工具类操作流
                    FileUtils.writeByteArrayToFile(new File(realPath, fileName), bs);  
                }catch(Exception ee){
                    throw new Exception("上传失败，写入文件失败，"+ee.getMessage());
                }
                
                path = "/file" + path;
                pathList.add(path);
                realPathList.add(realPath);
            }catch (Exception e) {                
                throw e;
            }        
        }
        return pathList;
    }

    public List<String> getPathList() {
        return pathList;
    }

    public void setPathList(List<String> pathList) {
        this.pathList = pathList;
    }

    public List<String> getRealPathList() {
        return realPathList;
    }

    public void setRealPathList(List<String> realPathList) {
        this.realPathList = realPathList;
    }

}
