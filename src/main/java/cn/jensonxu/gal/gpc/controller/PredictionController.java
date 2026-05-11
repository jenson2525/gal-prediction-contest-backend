package cn.jensonxu.gal.gpc.controller;

import cn.jensonxu.gal.gpc.common.CommonResult;
import cn.jensonxu.gal.gpc.common.request.PredictionSubmitRequest;
import cn.jensonxu.gal.gpc.repository.entity.Prediction;
import cn.jensonxu.gal.gpc.service.PredictionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/** 竞猜模块接口层，提供竞猜提交与查询相关接口 */
@RestController
@RequestMapping("/prediction")
@RequiredArgsConstructor
public class PredictionController {

    private final PredictionService predictionService;

    /** 提交竞猜 */
    @PostMapping("/submit")
    public CommonResult<Void> submit(@RequestBody @Valid PredictionSubmitRequest request) {
        predictionService.submit(request);
        return CommonResult.success();
    }

    /** 按 QQ 号查询竞猜记录 */
    @GetMapping("/query")
    public CommonResult<Prediction> query(@RequestParam String qqNumber) {
        return CommonResult.success(predictionService.query(qqNumber));
    }
}