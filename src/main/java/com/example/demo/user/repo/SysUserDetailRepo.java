package com.example.demo.user.repo;

import com.example.demo.user.dto.UserDetailDTO;
import com.example.demo.user.entity.SysUserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SysUserDetailRepo extends JpaRepository<SysUserDetail,String > {
    Optional<SysUserDetail> findByUserId(String id);
}
