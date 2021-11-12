package com.example.test.dal.dao;


import com.example.test.dal.database.DatabaseContextHolder;
import com.example.test.dal.database.DatabaseType;
import com.example.test.dal.mapper.TestCaseDefineApiAutoDOMapper;
import com.example.test.dal.moduleDO.TestCaseDefineApiAutoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2020/8/19.
 */
@Repository
@Component
public class TestCaseDefineApiSAutoDODao {

    @Autowired
    TestCaseDefineApiAutoDOMapper testCaseDefineApiAutoDOMapper;



    /**
     * 查询测试用例配置表
     */
    public List<TestCaseDefineApiAutoDO> getTestCaseDefineApiAutoDO(Long Id) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.toceandb);
        return testCaseDefineApiAutoDOMapper.getTestCaseDefineApiAutoDO(Id);
    }


     /**
     * 插入TestCase
     */
    public int insertTestCaseDefineApiAutoAll(List<TestCaseDefineApiAutoDO> list){
        DatabaseContextHolder.setDatabaseType(DatabaseType.toceandb);
      return testCaseDefineApiAutoDOMapper.insertTestCaseDefineApiAutoAll(list);
    }


}

