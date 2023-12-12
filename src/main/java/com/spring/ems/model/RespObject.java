package com.spring.ems.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


//Class to define server response structure
@AllArgsConstructor
public class RespObject {
    public String message;

    public String data;

    public String token;

    public RespObject() {
        this.message = "";
        this.data="";
        this.token="";
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t message='" + message + "',\n" +
                "\t data='" + data + "',\n" +
                "\t token='" + token + "',\n" +
                '}';
    }

    public String print(){
        return "{\n" +
                "\t message='" + message + "',\n" +
                "\t data='" + data + "',\n" +
                "\t token='" + token + "',\n" +
                '}';
    }

}
