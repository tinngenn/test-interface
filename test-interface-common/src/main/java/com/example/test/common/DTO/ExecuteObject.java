package com.example.test.common.DTO;

import com.example.test.common.DTO.cell.Env;
import com.example.test.common.DTO.cell.Execute;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class ExecuteObject {
    private Env env;

    private List<Execute> execute;
}


