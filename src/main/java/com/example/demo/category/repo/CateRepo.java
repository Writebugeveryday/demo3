package com.example.demo.category.repo;

import com.example.demo.category.entity.CateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CateRepo extends JpaRepository<CateEntity , String > {

   Optional<CateEntity> findByIdAndIsDelete(String id, String isDelete);
}
