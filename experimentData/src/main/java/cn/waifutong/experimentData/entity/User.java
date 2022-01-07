package cn.waifutong.experimentData.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;//主键
    private String username;
    private String password;
    private String name;
    private String phone;
    private Date insertDate;
    private String delFlag; //0:正常   1:已删除

}
