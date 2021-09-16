package com.itfeng.exception;

public class AppException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AppException(Exception e){
        super(e);
    }
    public AppException(String message){
        this("500", message);
    }
    public AppException(String code, String message){
        super(message);
        setCode(code);
    }
}
