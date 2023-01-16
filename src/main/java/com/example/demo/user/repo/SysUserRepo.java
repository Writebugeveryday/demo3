package com.example.demo.user.repo;

import com.example.demo.user.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SysUserRepo extends JpaRepository<SysUser, String > {

    Optional<SysUser> findByNameAndStatusIsNot (String name, String status);
}
