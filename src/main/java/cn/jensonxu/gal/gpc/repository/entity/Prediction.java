package cn.jensonxu.gal.gpc.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/** 竞猜记录实体，对应 prediction 表 */
@Data
@TableName("prediction")
public class Prediction {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String top8Ids;
    private Integer championId;
    private LocalDateTime createdAt;
}