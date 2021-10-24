package com.example.test.dal.service;


import com.example.test.dal.dao.TestCaseDefineApiSAutoDODao;
import com.example.test.dal.dao.TestCaseDefineApiSAutoStepDODao;
import com.example.test.dal.moduleDO.TestCaseDefineApiAutoDO;
import com.example.test.dal.moduleDO.TestCaseDefineApiAutoStepDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by admin on 2020/8/20.
 */
@Slf4j
@Service
public class TestCaseDefineApiAutoService {

    /*商户协议*/
    @Autowired
    TestCaseDefineApiSAutoDODao testCaseDefineApiSAutoDODao;



    /**
     * 通过testCaseId 查询测试用例表配置
     * @param  defineld   用例配置关联id
     * @return TestCaseDefineApiAutoStepDO   表数据DO对象
     */

    public List<TestCaseDefineApiAutoDO> getTestCaseDefineApiAutoDO(Long defineld) {

        return testCaseDefineApiSAutoDODao.getTestCaseDefineApiAutoDO(defineld);

    }

     /**
     * 批量插入Test_case测试用例表
     * @param  list   List<TestCaseDefineApiAutoStepDO> 批量插入表数据List对象
     * @return int   返回插入状态
     */


    public int insertTestCaseDefineApiAutoAll(List<TestCaseDefineApiAutoDO> list) {

        return testCaseDefineApiSAutoDODao.insertTestCaseDefineApiAutoAll(list);
    }




}
