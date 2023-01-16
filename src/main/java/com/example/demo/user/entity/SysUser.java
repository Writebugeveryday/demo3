package com.example.demo.user.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sys_user")
public class SysUser {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "pwd")
    private String pwd;
    @Column(name = "money")
    private Integer money;
    /*
    状态 0：删除  1：商家  2：用户
     */
    @Column(name = "status")
    private String status;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "update_time")
    private String updateTime;
}


