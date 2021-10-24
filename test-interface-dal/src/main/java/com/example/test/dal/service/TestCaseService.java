package com.example.test.dal.service;


import com.example.test.dal.dao.TestCaseDODao;
import com.example.test.dal.moduleDO.TestCaseDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



/**
 * Created by admin on 2020/8/20.
 */
@Slf4j
@Service
public class TestCaseService {

    /*商户协议*/
    @Autowired
    TestCaseDODao testCaseDODao;



    /**
     * 通过testCaseId 查询测试用例表配置
     * @param  testCaseId   测试用例id
     * @return TestCaseDO   表数据DO对象
     */

    public List<TestCaseDO> getTestCase(String testCaseId) {

        return testCaseDODao.getTestCase(testCaseId);

    }

    /**
     * 批量插入Test_case测试用例表
     * @param  list   List<TestCaseDO> 批量插入表数据List对象
     * @return int   返回插入状态
     */

    public int insertTestCaseAll(List<TestCaseDO> list) {

        return testCaseDODao.insertTestCaseAll(list);
    }

    /**
     * 批量插入Test_case测试用例表
     * @param  testCaseDO   testCaseDO更新对象
     * @return int         返回更新状态
     */

    public int updateTestCaseID(TestCaseDO testCaseDO) {

        return testCaseDODao.updateTestCaseID(testCaseDO);
    }



}
