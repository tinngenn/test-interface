package com.example.test.common.DTO.cell;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Setter
@Getter
@ToString
public class Root
{
    private Env env;

    private List<Execute> execute;


}
