package com.example.test.dal.moduleDO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;


@Getter
@Setter
@ToString(callSuper = true)
public class TestCaseDefineApiAutoDO {
    private Long id;

    private Long tenantId;

    private Date createdAt;

    private Date modifiedAt;

}