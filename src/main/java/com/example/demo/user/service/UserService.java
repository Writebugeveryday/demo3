package com.example.demo.user.service;

import com.example.demo.Constant;
import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.dto.UserDetailDTO;
import com.example.demo.user.entity.SysUser;
import com.example.demo.user.entity.SysUserDetail;
import com.example.demo.user.repo.SysUserDetailRepo;
import com.example.demo.user.repo.SysUserRepo;
import com.example.demo.util.IdWorker;
import com.neuqsoft.commons.spring.dto.MessageDTO;
import com.neuqsoft.commons.spring.exception.GlobalException;
import com.neuqsoft.commons.util.DateUtils;
import com.neuqsoft.commons.util.MD5Utils;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    SysUserRepo userRepo;
    @Autowired
    SysUserDetailRepo userDetailRepo;

    @Autowired
    IdWorker idWorker;

    public void addPerson(UserDTO dto, String status){
        Optional<SysUser> optional =  userRepo.findByNameAndStatusIsNot(dto.getName(), Constant.Status.DELETE);
        if (optional.isPresent()){
            throw new GlobalException(Constant.ErrorCode.USER_EXIT);
        }

        SysUser user = new SysUser();
        user.setId(idWorker.nextId());
        user.setName(dto.getName());
        user.setMoney(500000);
        user.setStatus(status);
        user.setPwd(MD5Utils.md5(dto.getPwd()));
        user.setCreateTime(DateUtils.now());
        user.setUpdateTime(DateUtils.now());
        user.setNickName(dto.getName());
        userRepo.save(user);
    }

    public void deletePerson(String id){
        SysUser user = userRepo.getById(id);
        user.setStatus(Constant.Status.DELETE);
        user.setUpdateTime(DateUtils.now());
        userRepo.save(user);
    }

    public MessageDTO<UserDTO> getPerson(String name ,String pwd){
        Optional<SysUser> optional = userRepo.findByNameAndStatusIsNot(name, Constant.Status.DELETE);
        if(optional.isPresent()){
            if (!Objects.equals(optional.get().getPwd(), MD5Utils.md5(pwd))){
                throw new GlobalException(Constant.ErrorCode.PWD_ERROR);
            }
        }else {
            throw new GlobalException(Constant.ErrorCode.USER_NOT_EXIT);
        }
        redisTemplate.opsForHash();
        UserDTO dto = new UserDTO();
        dto.setId(optional.get().getId());
        dto.setName(optional.get().getName());
        return MessageDTO.ok(dto);
    }

    public MessageDTO<UserDTO> getPersonById(String id){
        Optional<SysUser> optional = userRepo.findById(id);
        if(optional.isPresent()){
            SysUser user = optional.get();
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setPwd(user.getPwd());
            return MessageDTO.ok(dto);
        }else {
            return MessageDTO.ok();
        }

    }

    public MessageDTO<UserDetailDTO> getDetail(String id) {
        UserDetailDTO dto = new UserDetailDTO();
        Optional<SysUserDetail> optional = userDetailRepo.findByUserId(id);
        if (optional.isPresent()) {
            SysUserDetail userDetail = optional.get();
            BeanUtils.copyProperties(userDetail,dto);
            return MessageDTO.ok(dto);
        } else {
            return MessageDTO.ok();
        }
    }
}
