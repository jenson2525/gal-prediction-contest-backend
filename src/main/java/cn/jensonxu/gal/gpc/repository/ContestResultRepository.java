package cn.jensonxu.gal.gpc.repository;

import cn.jensonxu.gal.gpc.repository.entity.*;
import cn.jensonxu.gal.gpc.repository.mapper.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 结果与积分数据访问层，负责结果录入、积分计算与排行榜查询 */
@Repository
@RequiredArgsConstructor
public class ContestResultRepository {

    private final Top8ResultMapper top8ResultMapper;
    private final ChampionResultMapper championResultMapper;
    private final PredictionMapper predictionMapper;
    private final ScoreMapper scoreMapper;
    private final UserMapper userMapper;

    /** 判断八强结果是否已公布 */
    public boolean existsTop8Result() {
        return top8ResultMapper.exists(null);
    }

    /** 判断冠军结果是否已公布 */
    public boolean existsChampionResult() {
        return championResultMapper.exists(null);
    }

    /** 保存八强结果 */
    public void saveTop8Result(Top8Result top8Result) {
        top8ResultMapper.insert(top8Result);
    }

    /** 保存冠军结果 */
    public void saveChampionResult(ChampionResult championResult) {
        championResultMapper.insert(championResult);
    }

    /** 查询所有竞猜记录 */
    public List<Prediction> findAllPredictions() {
        return predictionMapper.selectList(null);
    }

    /** 按用户 ID 查询用户 */
    public User findUserById(Integer userId) {
        return userMapper.selectById(userId);
    }

    /** 新增或累加用户积分 */
    public void saveOrUpdateScore(Integer userId, int delta) {
        Score existing = scoreMapper.selectOne(
                new LambdaQueryWrapper<Score>()
                        .eq(Score::getUserId, userId)
        );
        if (existing == null) {
            Score score = new Score();
            score.setUserId(userId);
            score.setScore(delta);
            scoreMapper.insert(score);
        } else {
            existing.setScore(existing.getScore() + delta);
            scoreMapper.updateById(existing);
        }
    }

    /** 按积分降序查询所有用户积分 */
    public List<Score> findScoresOrderByScoreDesc() {
        return scoreMapper.selectList(
                new LambdaQueryWrapper<Score>()
                        .orderByDesc(Score::getScore)
        );
    }
}