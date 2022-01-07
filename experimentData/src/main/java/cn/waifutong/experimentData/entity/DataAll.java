package cn.waifutong.experimentData.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataAll {

    private Long id;//主键
    private String model_type;//模型类型
    private String num_layer;//神经网络层数
    private String num_node;//神经网络节点数
    private String pred;//模拟性能
    private String exp_type;//数据类型
    private String technique;//热处理工艺
    private String num_cycle;//升温降温循环次数

    /**  注入csv字段  **/
    private String temp1;//保温温度
    private String time1;//保温时间
    private String irate1;//加热速率
    private String drate1;//冷却速率
    private String sigma1;//抗拉强度σb
    private String sigma0p21;//屈服极限σ0.2
    private String delta1;//伸长率δ
    private String psai1;//断面收缩率ψ
    private String tau1;//剪切强度τ
    private String temp2 ;//保温温度2
    private String time2;//保温时间2
    private String irate2;//加热速率2
    private String drate2;//冷却速率2
    private String sigma2;//抗拉强度σb2
    private String sigma0p22;//屈服极限σ0.22
    private String delta2;//伸长率δ2
    private String psai2;//断面收缩率ψ2
    private String tau2;//剪切强度τ2
    private String temp3;//保温温度3
    private String time3;//保温时间3
    private String irate3;//加热速率3
    private String drate3;//冷却速率3
    private String sigma3;//抗拉强度σb3
    private String sigma0p23;//屈服极限σ0.23
    private String delta3;//伸长率δ3
    private String psai3;//4
    private String tau3;//剪切强度τ3
    private String cti;//钛（Ti）
    private String cal;//铝（Al）
    private String cv;//钒（V）
    private String cfe;//铁（Fe）
    private String cc;//碳（）
    private String ch;//氢(H)
    private String co;//氧（O）
    private String guige;//规格

    /**   以下字段不影响实验 **/
    private String beta_temp;//β相转变温度
    private String phase;//相结构
    private String pici;//批次
    private String pihao;//批号
    private String jiequxuhao;//截取序号
    private String diameter_min;//最小直径
    private String diameter_max;//最大直径
    private Long data_group_id;//关联数据组Id
    private Long input_id;//关联入参Id
    private Long output_id;//关联出参Id
}
