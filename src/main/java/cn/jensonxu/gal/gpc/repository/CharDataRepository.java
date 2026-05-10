package cn.jensonxu.gal.gpc.repository;

import cn.jensonxu.gal.gpc.repository.entity.CharData;
import cn.jensonxu.gal.gpc.repository.mapper.CharDataMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 角色数据访问层，负责 char_data 表的查询操作 */
@Repository
@RequiredArgsConstructor
public class CharDataRepository {

    private final CharDataMapper charDataMapper;

    /** 查询全量角色列表 */
    public List<CharData> findAll() {
        return charDataMapper.selectList(null);
    }

    /** 按关键词模糊搜索角色，匹配 name 或 cn_name 字段 */
    public List<CharData> findByKeyword(String keyword) {
        return charDataMapper.selectList(
                new LambdaQueryWrapper<CharData>()
                        .like(CharData::getName, keyword)
                        .or()
                        .like(CharData::getCnName, keyword)
        );
    }
}