package com.example.test.dal.dao;


import com.example.test.dal.database.DatabaseContextHolder;
import com.example.test.dal.database.DatabaseType;
import com.example.test.dal.mapper.TestCaseDOMapper;
import com.example.test.dal.moduleDO.TestCaseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2020/8/19.
 */
@Repository
@Component
public class TestCaseDODao {

    @Autowired
    TestCaseDOMapper testCaseDOMapper;



    /**
     * 查询测试用例表
     */
    public List<TestCaseDO> getTestCase(String testCaseId) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.toceandb);
        return testCaseDOMapper.getTestCase(testCaseId);
    }


    /**
     * 插入TestCase
     */
    public int insertTestCaseAll(List<TestCaseDO> list){
        DatabaseContextHolder.setDatabaseType(DatabaseType.toceandb);
      return testCaseDOMapper.insertTestCaseAll(list);
    }

    /**
     * 更新TestCase
     */
    public int updateTestCaseID(TestCaseDO TestCaseDO){
        DatabaseContextHolder.setDatabaseType(DatabaseType.toceandb);
        return testCaseDOMapper.updateTestCaseID(TestCaseDO);
    }
}

