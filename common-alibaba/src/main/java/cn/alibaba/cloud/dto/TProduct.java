package cn.alibaba.cloud.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value = "t_product")
public class TProduct {
    @TableId
    private Long id;
    private String productName;
    private Integer productStatus;
    private Timestamp createTime;
    private Double productPrice;
}
