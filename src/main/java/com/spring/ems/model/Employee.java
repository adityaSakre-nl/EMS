package com.spring.ems.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class Employee {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String employeeId;

    private String employeeName;

    private String  role;

    private String email;

    private String password;


    public String printProtected()
    {
       return "{\n" +
                "\t'employeeId': '"+getEmployeeId() +"',\n"+
                "\t'employeeName': '"+getEmployeeName() +"',\n"+
                "\t'role': '"+getRole() +"',\n"+
                "\t'email': '"+getEmail()+"',\n"+
                "\n}";
    }
}
