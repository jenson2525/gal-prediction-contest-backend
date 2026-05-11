package cn.jensonxu.gal.gpc.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/** 用户积分实体，对应 score 表 */
@Data
@TableName("score")
public class Score {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer score;
    private LocalDateTime calculatedAt;
}