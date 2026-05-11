package cn.jensonxu.gal.gpc.service;

import cn.jensonxu.gal.gpc.common.request.PredictionSubmitRequest;
import cn.jensonxu.gal.gpc.repository.PredictionRepository;
import cn.jensonxu.gal.gpc.repository.entity.Prediction;
import cn.jensonxu.gal.gpc.repository.entity.User;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/** 竞猜业务层，处理竞猜提交与查询相关业务逻辑 */
@Service
@RequiredArgsConstructor
public class PredictionService {

    private final PredictionRepository predictionRepository;

    /** 提交竞猜，含业务规则校验、用户创建、selected 累加 */
    @Transactional
    public void submit(PredictionSubmitRequest request) {
        List<Integer> top8Ids = request.getTop8Ids();
        long distinctCount = top8Ids.stream().distinct().count();
        if (distinctCount != 8) {
            throw new IllegalArgumentException("八强角色不能重复");
        }
        if (!top8Ids.contains(request.getChampionId())) {
            throw new IllegalArgumentException("冠军角色必须在八强角色之中");
        }

        User user = predictionRepository.findUserByQq(request.getQqNumber());
        if (user == null) {
            user = predictionRepository.saveUser(request.getQqNumber());
        }
        if (predictionRepository.existsPredictionByUserId(user.getId())) {
            throw new IllegalArgumentException("该QQ号已提交过竞猜，不能重复提交");
        }
        Prediction prediction = new Prediction();
        prediction.setUserId(user.getId());
        prediction.setTop8Ids(top8Ids.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")));
        prediction.setChampionId(request.getChampionId());
        predictionRepository.savePrediction(prediction);
        predictionRepository.incrementSelectedForChars(top8Ids);
    }

    /** 按 QQ 号查询竞猜记录 */
    public Prediction query(String qqNumber) {
        User user = predictionRepository.findUserByQq(qqNumber);
        if (user == null) {
            throw new IllegalArgumentException("该QQ号尚未提交竞猜");
        }
        return predictionRepository.findPredictionByUserId(user.getId());
    }
}