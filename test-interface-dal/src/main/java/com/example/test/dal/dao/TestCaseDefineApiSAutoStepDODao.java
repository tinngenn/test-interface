package com.example.test.dal.dao;


import com.example.test.dal.database.DatabaseContextHolder;
import com.example.test.dal.database.DatabaseType;
import com.example.test.dal.mapper.TestCaseDefineApiAutoStepDOMapper;
import com.example.test.dal.moduleDO.TestCaseDefineApiAutoStepDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2020/8/19.
 */
@Repository
@Component
public class TestCaseDefineApiSAutoStepDODao {

    @Autowired
    TestCaseDefineApiAutoStepDOMapper   testCaseDefineApiAutoStepDOMapper;



    /**
     * 查询测试用例配置表
     */
    public List<TestCaseDefineApiAutoStepDO> getTestCaseDefineApiAutoStepDO(Long defineld) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.toceandb);
        return testCaseDefineApiAutoStepDOMapper.getTestCaseDefineApiAutoStepDO(defineld);
    }


     /**
     * 插入TestCase
     */
    public int insertTestCaseDefineApiAutoStepAll(List<TestCaseDefineApiAutoStepDO> list){
        DatabaseContextHolder.setDatabaseType(DatabaseType.toceandb);
      return testCaseDefineApiAutoStepDOMapper.insertTestCaseDefineApiAutoStepAll(list);
    }


}

