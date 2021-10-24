package com.example.test.common.DTO.cell.tools;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class Config {

    private  Integer retry = 0;

    private  String timeout;

    private  Boolean http2 = true;

    private  Boolean methodRewriting = true;

    private  Boolean rejectUnauthorized = true;

}
