package cn.jensonxu.gal.gpc.common.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class Top8ResultRequest {

    @NotNull(message = "八强角色不能为空")
    @Size(min = 8, max = 8, message = "八强角色必须恰好为8个")
    private List<Integer> top8Ids;
}