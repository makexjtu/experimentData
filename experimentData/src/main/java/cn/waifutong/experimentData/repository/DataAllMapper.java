package cn.waifutong.experimentData.repository;

import cn.waifutong.experimentData.entity.User;

import java.util.List;
import java.util.Map;

public interface DataAllMapper {

    List<Map<String, Object>> selectDataAll();
}
