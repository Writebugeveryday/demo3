package com.example.demo.category.api;

import com.example.demo.category.dto.CateDTO;
import com.example.demo.category.service.CateService;
import com.neuqsoft.commons.spring.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/goods")
public class CateApi {

    @Autowired
    CateService cateService;

    /**
     * 查找所有种类信息
     * @return list
     */
    @GetMapping("")
    public MessageDTO<List<CateDTO>> findAllCate(){
        return cateService.finaAllCate();
    }

    /**
     * 添加种类信息
     * @param dto dto
     */
    @PostMapping("")
    public MessageDTO<Void> addCate(@RequestBody CateDTO dto){
        return cateService.addCate(dto);
    }

    /**
     * 编辑种类
     * @param id id
     * @param dto dto
     */
    @PostMapping("/{id}")
    public MessageDTO<Void> editCate(@PathVariable String id,@RequestBody CateDTO dto){
        return cateService.editCate(id, dto);
    }

    /**
     * 删除种类信息
     * @param id id
     */
    @PostMapping("/delete")
    public MessageDTO<Void> deleteCate(@RequestParam String id){
        return cateService.deleteCate(id);
    }

    @GetMapping("/{id}")
    public MessageDTO<CateDTO> findById(@PathVariable String id){
        return cateService.findById(id);
    }
}
