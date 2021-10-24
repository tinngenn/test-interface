package com.example.test.common.DTO.cell;



import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class HttpServer {
    private String serverId;

    private String hostname;

    private String protocol;

    private int port;

    private List<Headers> headers;

}