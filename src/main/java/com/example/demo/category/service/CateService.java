package com.example.demo.category.service;

import com.example.demo.Constant;
import com.example.demo.category.dto.CateDTO;
import com.example.demo.category.entity.CateEntity;
import com.example.demo.category.repo.CateRepo;
import com.neuqsoft.commons.spring.dto.MessageDTO;
import com.neuqsoft.commons.spring.exception.GlobalException;
import com.neuqsoft.commons.util.DateUtils;
import com.neuqsoft.commons.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CateService {
    @Autowired
    CateRepo cateRepo;
    @Autowired
    IdWorker idWorker;

    /**
     * 获取全部种类信息
     * @return List
     */
    public MessageDTO<List<CateDTO>> finaAllCate(){
        return MessageDTO.ok(
                cateRepo.findAll().stream()
                        .filter(e-> !Objects.equals(e.getIsDelete(), "1"))
                        .map(e->{
                            CateDTO dto = new CateDTO();
                            dto.setId(e.getId());
                            dto.setCateName(e.getCateName());
                            return dto;
                        }).collect(Collectors.toList())
        );
    }

    /**
     * 添加种类
     * @param dto dto
     */
    public MessageDTO<Void> addCate(CateDTO dto){
        CateEntity entity = new CateEntity();
        entity.setId(idWorker.nextId());
        entity.setCateName(dto.getCateName());
        entity.setCreateTime(DateUtils.now());
        entity.setUpdateTime(DateUtils.now());
        entity.setIsDelete(Constant.IsDelete.NOT_DELETE);
        cateRepo.save(entity);
        return MessageDTO.ok();
    }

    /**
     * 修改种类
     * @param id 种类id
     * @param dto dto
     */
    public MessageDTO<Void> editCate(String id, CateDTO dto){
        Optional<CateEntity> optional = cateRepo.findByIdAndIsDelete(id, Constant.IsDelete.NOT_DELETE);
        if (optional.isPresent()){
            CateEntity entity = optional.get();
            entity.setCateName(dto.getCateName());
            entity.setUpdateTime(DateUtils.now());
            cateRepo.save(entity);
        }else {
            throw new GlobalException(Constant.ErrorCode.CATE_NOT_EXIT);
        }
        return MessageDTO.ok();
    }

    /**
     * 删除种类信息
     * @param id id
     */
    public MessageDTO<Void> deleteCate(String id){
        Optional<CateEntity> optional = cateRepo.findByIdAndIsDelete(id, Constant.IsDelete.NOT_DELETE);
        if (optional.isPresent()){
            cateRepo.delete(optional.get());
        }else {
            throw new GlobalException(Constant.ErrorCode.CATE_NOT_EXIT);
        }
        return MessageDTO.ok();
    }

    public MessageDTO<CateDTO> findById(String id){
        Optional<CateEntity> optional = cateRepo.findById(id);
        if (optional.isPresent()){
            CateEntity entity = new CateEntity();
            CateDTO dto = new CateDTO();
            dto.setId(entity.getId());
            dto.setCateName(entity.getCateName());
            return MessageDTO.ok(dto);
        }else {
            throw new GlobalException(Constant.ErrorCode.CATE_NOT_EXIT);
        }
    }
}
