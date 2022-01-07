package cn.waifutong.experimentData.service;

import cn.waifutong.experimentData.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


//update
public interface DataAllService {
    List<Map<String, Object>> selectDataAll();
    public String excelImport(MultipartFile file) throws IOException;
}
