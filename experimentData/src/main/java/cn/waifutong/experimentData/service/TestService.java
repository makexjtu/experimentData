package cn.waifutong.experimentData.service;

import cn.waifutong.experimentData.entity.DataAll;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;



public interface TestService {

    List<DataAll> readExcel(MultipartFile file) throws IOException;

    void addTestAll(Long userId);
}
