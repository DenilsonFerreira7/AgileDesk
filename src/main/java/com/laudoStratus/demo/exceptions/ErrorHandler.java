package com.laudoStratus.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorHandler {


    private String campo;
    private String message;
    public  ErrorHandler(String message){
        this.message =message;
    }

}
