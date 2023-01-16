package com.example.demo.category.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "goods_cate")
public class CateEntity {
    
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "cate_name")
    private String cateName;

    @Column(name = "is_delete")
    private String isDelete;
    
    @Column(name = "create_time")
    private String createTime;
    
    @Column(name = "update_time")
    private String updateTime;
}
