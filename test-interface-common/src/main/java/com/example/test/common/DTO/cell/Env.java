package com.example.test.common.DTO.cell;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Env
{
    private String name;

    private List<HttpServer> httpServer;

    private List<DataSource> dataSource;

    private Variable variable;


}
