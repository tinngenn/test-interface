package com.example.test.common.DTO.cell;

import com.example.test.common.DTO.cell.tools.Config;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DataSource {
    private int type;

    private String host;

    private String user;

    private String password;

    private String serverId;

    private Config config;


}
