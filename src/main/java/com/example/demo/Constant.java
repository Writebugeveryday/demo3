package com.example.demo;

import com.neuqsoft.commons.spring.dto.IMessageCode;
import lombok.Getter;

public class Constant {

    public static class Status {
        public static final String DELETE = "0";
        public static final String BUSINESS = "1";
        public static final String CUSTOMER = "2";
    }

    public static class IsDelete{
        /*
        未删除
         */
        public static final String NOT_DELETE = "0";
        /*
        已删除
         */
        public static final String IS_DELETE = "1";

    }
    @Getter
    public enum ErrorCode implements IMessageCode {
        USER_EXIT("90000","用户已存在"),
        USER_NOT_EXIT("90001","用户不存在"),
        PWD_ERROR("90002","密码错误"),
        CATE_NOT_EXIT("90003","种类不存在");
        private final String code;
        private final String message;

        ErrorCode(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }


}
