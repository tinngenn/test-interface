package com.example.test.dal.moduleDO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString(callSuper = true)
public class TestCaseDO {

    private Long    id;

    private Long    tenantId;

    private String  name;

    private Date    createdAt;

    private Date    modifiedAt;

    private Long    createdBy;

    private Long    modifiedBy;

    private Long    libraryId;

    private String  testCaseCode;

    private String  testCaseType;

    private Byte    priority;

    private String  treePath;
}
