package com.example.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientException extends Exception{

    private static final long serialVersionUID = 2062075073138612128L;
    private String code;
    private String message;
    private Exception exc;

    public ClientException (String code, String message){
        this.code=code;
        this.message=message;
    }
}
