package com.lguilherme.bookstoragemanager.Exception;

public class UpdateHasNoChangeException extends  RuntimeException {

    private  static  final long serialVersionUID = 798556946957415176L;

    public  UpdateHasNoChangeException(String message) {
        super(message);
    }

    public  UpdateHasNoChangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
