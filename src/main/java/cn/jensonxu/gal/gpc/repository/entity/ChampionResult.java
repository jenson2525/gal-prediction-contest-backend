package cn.jensonxu.gal.gpc.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/** 冠军结果实体，对应 champion_result 表 */
@Data
@TableName("champion_result")
public class ChampionResult {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer championId;
    private LocalDateTime announcedAt;
}