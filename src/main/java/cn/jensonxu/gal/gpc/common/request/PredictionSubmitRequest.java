package cn.jensonxu.gal.gpc.common.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class PredictionSubmitRequest {

    @NotBlank(message = "QQ号不能为空")
    private String qqNumber;

    @NotNull(message = "八强角色不能为空")
    @Size(min = 8, max = 8, message = "八强角色必须恰好选择8个")
    private List<Integer> top8Ids;

    @NotNull(message = "冠军角色不能为空")
    private Integer championId;
}