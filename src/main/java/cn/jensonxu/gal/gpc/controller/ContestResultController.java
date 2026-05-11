package cn.jensonxu.gal.gpc.controller;

import cn.jensonxu.gal.gpc.common.CommonResult;
import cn.jensonxu.gal.gpc.common.request.ChampionResultRequest;
import cn.jensonxu.gal.gpc.common.request.Top8ResultRequest;
import cn.jensonxu.gal.gpc.service.ContestResultService;
import cn.jensonxu.gal.gpc.common.vo.LeaderboardVO;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** 结果与积分模块接口层，提供结果录入与排行榜查询相关接口 */
@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
public class ContestResultController {

    private final ContestResultService contestResultService;

    /** 录入八强结果 */
    @PostMapping("/top8")
    public CommonResult<Void> announceTop8(@RequestBody @Valid Top8ResultRequest request) {
        contestResultService.announceTop8(request);
        return CommonResult.success();
    }

    /** 录入冠军结果 */
    @PostMapping("/champion")
    public CommonResult<Void> announceChampion(@RequestBody @Valid ChampionResultRequest request) {
        contestResultService.announceChampion(request);
        return CommonResult.success();
    }

    /** 查询积分排行榜 */
    @GetMapping("/leaderboard")
    public CommonResult<List<LeaderboardVO>> getLeaderboard() {
        return CommonResult.success(contestResultService.getLeaderboard());
    }

    /** 查询积分最高的用户 */
    @GetMapping("/leaderboard/top")
    public CommonResult<LeaderboardVO> getTopUser() {
        return CommonResult.success(contestResultService.getTopUser());
    }
}