package com.tpe.exception;

public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);//Parenta gider ve oradaki tek parametreli constructorda bizim yazdigimiz string messagei calistirir.
    }
}
