package com.imooc.luckymoney.enums;

public enum  ResultEnum {
    UNKONW_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    POOL(100,"你可能是个穷人"),
    RICH(101,"你可能是个富人");


    private Integer code;
    private String msg;

    ResultEnum(Integer code , String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
}
