package cn.jensonxu.gal.gpc.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/** 八强结果实体，对应 top8_result 表 */
@Data
@TableName("top8_result")
public class Top8Result {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String top8Ids;
    private LocalDateTime announcedAt;
}