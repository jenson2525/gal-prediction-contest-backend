package cn.jensonxu.gal.gpc.common.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChampionResultRequest {

    @NotNull(message = "冠军角色不能为空")
    private Integer championId;
}