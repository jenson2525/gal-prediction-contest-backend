package cn.jensonxu.gal.gpc.controller;

import cn.jensonxu.gal.gpc.common.CommonResult;
import cn.jensonxu.gal.gpc.repository.entity.CharData;
import cn.jensonxu.gal.gpc.service.CharacterService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** 角色模块接口层，提供角色查询相关接口 */
@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    /** 获取全量角色列表 */
    @GetMapping("/all")
    public CommonResult<List<CharData>> listAll() {
        return CommonResult.success(characterService.listAll());
    }

    @GetMapping("/search")
    public CommonResult<List<CharData>> search(@RequestParam String keyword) {
        return CommonResult.success(characterService.search(keyword));
    }
}