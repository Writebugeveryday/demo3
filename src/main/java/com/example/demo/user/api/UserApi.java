package com.example.demo.user.api;

import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.dto.UserDetailDTO;
import com.example.demo.user.service.UserService;
import com.example.demo.util.IdWorker;
import com.neuqsoft.commons.spring.dto.MessageDTO;
import com.neuqsoft.commons.util.DateUtils;
import com.neuqsoft.commons.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserApi {

    @Autowired
    UserService userService;

    @PostMapping("")
    public void addPerson(@RequestBody UserDTO dto, @RequestParam String status){
        userService.addPerson(dto,status);
    }

    @PostMapping("/delete")
    public void deletePerson(@RequestParam String id){
        userService.deletePerson(id);
    }

    @GetMapping("")
    public MessageDTO<UserDTO> getPerson(@RequestParam String name, @RequestParam String pwd){
        return userService.getPerson(name, pwd);
    }
    @GetMapping("/{id}")
    public MessageDTO<UserDTO> getPersonById(@PathVariable String id){
        return userService.getPersonById(id);
    }

    @GetMapping("/detail")
    public MessageDTO<UserDetailDTO> getDetail(@RequestParam String id){
        return userService.getDetail(id);
    }

}
