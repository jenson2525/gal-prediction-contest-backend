package cn.jensonxu.gal.gpc.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("char_data")
public class CharData {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String cnName;
    private String imageUrl;
    private Integer selected;
}