package com.example.test.dal.moduleDO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString(callSuper = true)
public class TestCaseDefineApiAutoStepDO {
    private Long id;

    private Long tenantId;

    private Date createdAt;

    private Date modifiedAt;

    private Long defineId;

    private String type;

    private String name;

    private Long parentId;

    private Integer index;

    private String struct;

}