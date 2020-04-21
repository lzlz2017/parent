package cn.alibaba.cloud.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@TableName(value = "t_product")
public class TProduct {
    @TableId
    private Long id;

    @NotBlank(message = "productName 不能为空")
    private String productName;

    private Integer productStatus;
    private Date createTime;
    private Double productPrice;
}
