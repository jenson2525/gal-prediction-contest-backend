package cn.jensonxu.gal.gpc.repository;

import cn.jensonxu.gal.gpc.repository.entity.CharData;
import cn.jensonxu.gal.gpc.repository.entity.Prediction;
import cn.jensonxu.gal.gpc.repository.entity.User;
import cn.jensonxu.gal.gpc.repository.mapper.CharDataMapper;
import cn.jensonxu.gal.gpc.repository.mapper.PredictionMapper;
import cn.jensonxu.gal.gpc.repository.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 竞猜数据访问层，负责用户与竞猜记录的数据库操作 */
@Repository
@RequiredArgsConstructor
public class PredictionRepository {

    private final UserMapper userMapper;
    private final PredictionMapper predictionMapper;
    private final CharDataMapper charDataMapper;

    /** 按 QQ 号查询用户 */
    public User findUserByQq(String qqNumber) {
        return userMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getQqNumber, qqNumber)
        );
    }

    /** 新增用户并返回含自增 id 的用户对象 */
    public User saveUser(String qqNumber) {
        User user = new User();
        user.setQqNumber(qqNumber);
        userMapper.insert(user);
        return user;
    }

    /** 判断该用户是否已提交过竞猜 */
    public boolean existsPredictionByUserId(Integer userId) {
        return predictionMapper.exists(
                new LambdaQueryWrapper<Prediction>()
                        .eq(Prediction::getUserId, userId)
        );
    }

    /** 保存竞猜记录 */
    public void savePrediction(Prediction prediction) {
        predictionMapper.insert(prediction);
    }

    /** 按用户 ID 查询竞猜记录 */
    public Prediction findPredictionByUserId(Integer userId) {
        return predictionMapper.selectOne(
                new LambdaQueryWrapper<Prediction>()
                        .eq(Prediction::getUserId, userId)
        );
    }

    /** 累加指定角色的 selected 字段 */
    public void incrementSelectedForChars(List<Integer> charIds) {
        charIds.forEach(id ->
                new LambdaUpdateChainWrapper<>(charDataMapper)
                        .eq(CharData::getId, id)
                        .setSql("selected = selected + 1")
                        .update()
        );
    }
}