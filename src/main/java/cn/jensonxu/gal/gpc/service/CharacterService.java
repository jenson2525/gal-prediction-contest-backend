package cn.jensonxu.gal.gpc.service;

import cn.jensonxu.gal.gpc.repository.CharDataRepository;
import cn.jensonxu.gal.gpc.repository.entity.CharData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/** 角色业务层，处理角色查询相关业务逻辑 */
@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharDataRepository charDataRepository;

    /** 获取全量角色列表 */
    public List<CharData> listAll() {
        return charDataRepository.findAll();
    }

    /** 按关键词搜索角色，keyword 不能为空 */
    public List<CharData> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException("搜索关键词不能为空");
        }
        return charDataRepository.findByKeyword(keyword);
    }
}