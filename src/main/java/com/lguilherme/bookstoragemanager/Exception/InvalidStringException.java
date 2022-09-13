package com.lguilherme.bookstoragemanager.Exception;

public class InvalidStringException  extends  RuntimeException{

    private static  final long serialVersionUID = 753671636726695487L;

    public InvalidStringException(String message){
        super(message);
    }

    public InvalidStringException(String message, Throwable cause){
        super(message, cause);
    }
}