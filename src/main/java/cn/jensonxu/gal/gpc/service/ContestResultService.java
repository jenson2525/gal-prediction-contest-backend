package cn.jensonxu.gal.gpc.service;

import cn.jensonxu.gal.gpc.common.request.ChampionResultRequest;
import cn.jensonxu.gal.gpc.common.request.Top8ResultRequest;
import cn.jensonxu.gal.gpc.repository.ContestResultRepository;
import cn.jensonxu.gal.gpc.repository.entity.ChampionResult;
import cn.jensonxu.gal.gpc.repository.entity.Top8Result;
import cn.jensonxu.gal.gpc.common.vo.LeaderboardVO;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/** 结果与积分业务层，处理结果录入、积分计算与排行榜查询 */
@Service
@RequiredArgsConstructor
public class ContestResultService {

    private final ContestResultRepository contestResultRepository;

    /** 录入八强结果并计算八强积分 */
    @Transactional
    public void announceTop8(Top8ResultRequest request) {
        if (contestResultRepository.existsTop8Result()) {
            throw new IllegalArgumentException("八强结果已公布，不能重复录入");
        }

        List<Integer> actualTop8Ids = request.getTop8Ids();
        if (actualTop8Ids.stream().distinct().count() != 8) {
            throw new IllegalArgumentException("八强角色不能重复");
        }

        Top8Result top8Result = new Top8Result();
        top8Result.setTop8Ids(actualTop8Ids.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")));
        contestResultRepository.saveTop8Result(top8Result);
        Set<Integer> actualTop8Set = new HashSet<>(actualTop8Ids);

        contestResultRepository.findAllPredictions().forEach(prediction -> {
            List<Integer> predictedIds = Arrays.stream(prediction.getTop8Ids().split(","))
                    .map(Integer::parseInt)
                    .toList();
            long matched = predictedIds.stream()
                    .filter(actualTop8Set::contains)
                    .count();
            if (matched > 0) {
                contestResultRepository.saveOrUpdateScore(prediction.getUserId(), (int) matched);
            }
        });
    }

    /** 录入冠军结果并为猜中冠军的用户加分 */
    @Transactional
    public void announceChampion(ChampionResultRequest request) {
        if (contestResultRepository.existsChampionResult()) {
            throw new IllegalArgumentException("冠军结果已公布，不能重复录入");
        }

        ChampionResult championResult = new ChampionResult();
        championResult.setChampionId(request.getChampionId());
        contestResultRepository.saveChampionResult(championResult);
        contestResultRepository.findAllPredictions().forEach(prediction -> {
            if (prediction.getChampionId().equals(request.getChampionId())) {
                contestResultRepository.saveOrUpdateScore(prediction.getUserId(), 1);
            }
        });
    }

    /** 查询积分排行榜，按积分降序返回 */
    public List<LeaderboardVO> getLeaderboard() {
        return contestResultRepository.findScoresOrderByScoreDesc().stream()
                .map(score -> new LeaderboardVO(
                        contestResultRepository.findUserById(score.getUserId()).getQqNumber(),
                        score.getScore()
                ))
                .toList();
    }

    /** 查询积分最高的用户 */
    public LeaderboardVO getTopUser() {
        List<LeaderboardVO> leaderboard = getLeaderboard();
        if (leaderboard.isEmpty()) {
            throw new IllegalArgumentException("暂无积分数据");
        }
        return leaderboard.getFirst();
    }
}