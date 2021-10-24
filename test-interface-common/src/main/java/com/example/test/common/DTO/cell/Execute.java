package com.example.test.common.DTO.cell;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Execute {

    private Variable variable;

    private int events;

    private int mode;

    private List<Steps> steps;

}