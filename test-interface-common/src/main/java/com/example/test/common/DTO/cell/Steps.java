package com.example.test.common.DTO.cell;



import com.example.test.common.DTO.cell.tools.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Setter
@Getter
@ToString
public class Steps
{
    private int type;

    private String method;

    private String path;

    private String hostname;

    private String protocol;

    private int port;

    private List<com.example.test.common.DTO.cell.tools.Params> Params;

    private Config config;

    private List<Assignment> assignment;

    private List<PostScript> postScript;

    private List<PreScript> preScript;

    private List<Assert>   asserts;
}
