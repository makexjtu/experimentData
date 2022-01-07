package cn.waifutong.experimentData.util;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 单个附件上传
 *
 */
public class AttachmentsUploadFile {

    /**
     * 相对路径
     */
    private String path = "";
    /**
     * 物理路径
     */
    private String realPath = "";
    private String prefix;
    private String suffix;
    private int fileSize;
    private String realFileName;
    private String originalFileName;

    public AttachmentsUploadFile(MultipartFile file, String type) {
        try {
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
            if ("product".equals(type)) {
                if ("win".equals(os.substring(0, 3).toLowerCase())) {
                    realPath = p.getProperty("product.winBasePath") + "/" + path;
                } else {
                    realPath = p.getProperty("product.linuxBasePath") + "/" + path;
                }
            }
            File folder = new File(realPath.replace("/", File.separator));
            if (!folder.exists() || !folder.isDirectory()) {
                folder.mkdirs();
            }

            String fileName = file.getOriginalFilename();
            originalFileName = fileName;
            if (fileName != null && !"".equals(fileName)) {
                fileName = FilenameUtils.getName(fileName);
                int lastIndexOfDot = fileName.lastIndexOf(".");
                prefix = fileName.substring(0, lastIndexOfDot);
                suffix = fileName.substring(lastIndexOfDot + 1);
                UUID uuid = UUID.randomUUID();
                String str = uuid.toString();
                // 去掉"-"符号
                String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23)
                        + str.substring(24);

                fileName = temp + "." + suffix;
                realFileName = fileName;
                path += fileName;
                realPath = realPath.replace(File.separator, "/");
                // 创建文件对象，表示要保存的头像文件,第一个参数表示存储的文件夹，第二个参数表示存储的文件
                File dest = new File(realPath, fileName);
                // 执行保存
                file.transferTo(dest);
                realPath += fileName;
                // System.out.println("attaupload:type:" + type);
                if (type == null || "".equals(type) || "null".equals(type)) {
                    path = "/file" + path;
                } else {
                    path = "/" + type + path;
                }
            } else {
                path = null;
                realPath = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    @Override
    public String toString() {
        return this.path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getRealFileName() {
        return realFileName;
    }

    public void setRealFileName(String realFileName) {
        this.realFileName = realFileName;
    }

}
