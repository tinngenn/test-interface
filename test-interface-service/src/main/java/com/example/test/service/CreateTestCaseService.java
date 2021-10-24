package com.example.test.service;



import com.example.test.common.Constants;
import com.example.test.dal.moduleDO.TestCaseDO;
import com.example.test.dal.moduleDO.TestCaseDefineApiAutoDO;
import com.example.test.dal.moduleDO.TestCaseDefineApiAutoStepDO;
import com.example.test.dal.service.TestCaseDefineApiAutoService;
import com.example.test.dal.service.TestCaseDefineApiAutoStepService;
import com.example.test.dal.service.TestCaseService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import utils.SpringBeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class CreateTestCaseService implements Runnable {

    TestCaseService testCaseService;

    TestCaseDefineApiAutoStepService testCaseDefineApiAutoStepService;

    TestCaseDefineApiAutoService testCaseDefineApiAutoService;


    private String testCaseId;
    private Integer index;

    public void setTestCaseId(String testCaseId,Integer index) {

        this.testCaseId = testCaseId;
        this.index = index;

    }

    public void setDbService(TestCaseService testCaseService,
                              TestCaseDefineApiAutoStepService testCaseDefineApiAutoStepService,
                              TestCaseDefineApiAutoService testCaseDefineApiAutoService) {

        this.testCaseService = testCaseService;
        this.testCaseDefineApiAutoStepService = testCaseDefineApiAutoStepService;
        this.testCaseDefineApiAutoService = testCaseDefineApiAutoService;
    }


    @Transactional(rollbackFor = Exception.class)
    public void copyTestCase()  {


        log.info("testCaseId:{}",testCaseId);
       try {
          TestCaseDO testCaseDO;
          TestCaseDO newTestCaseDO;//插入后更新都最新自增id
          Long defineId;
          Long newDefineId;//插入后更新都最新自增id
          //查询用例表，实际只有一条数据
          testCaseDO = testCaseService.getTestCase(testCaseId).get(0);
          defineId = testCaseDO.getDefineId();
          log.debug("查询用例表返回：{}", testCaseDO.toString());

          //查询用例配置自动表，实际只有一条数据
          List<TestCaseDefineApiAutoDO> testCaseDefineApiAutoDOList
                  = testCaseDefineApiAutoService.getTestCaseDefineApiAutoDO(defineId);

          log.info("查询用例自动表结果：{}", testCaseDefineApiAutoDOList.toString());
          //此时插入表拿到了最新的插入自增id
          log.info("插入用例自动表返回状态码：{}",
                  testCaseDefineApiAutoService.insertTestCaseDefineApiAutoAll(testCaseDefineApiAutoDOList));

          newDefineId = testCaseDefineApiAutoDOList.get(0).getId();
          log.info("newDefineId:{}", String.valueOf(newDefineId));
          //查询用例配置表
          List<TestCaseDefineApiAutoStepDO> testCaseDefineApiAutoStepDOList
                  = testCaseDefineApiAutoStepService.getTestCaseDefineApiAutoStepDO(defineId);

          log.debug("用例配置表查询结果前：{}", testCaseDefineApiAutoStepDOList.toString());
          //修正插入testCaseDefineApiAutoStep表配置的define_id
          amendStepParm(testCaseDefineApiAutoStepDOList, newDefineId);
          log.debug("用例配置表查询结果后：{}", testCaseDefineApiAutoStepDOList.toString());

          //复制插入用例复制表
          log.info("插入用例配置表返回状态码：{}",
                  testCaseDefineApiAutoStepService.insertTestCaseDefineApiAutoStepAll(testCaseDefineApiAutoStepDOList));
          log.debug(testCaseDefineApiAutoStepDOList.toString());

          //插入用例配置表
          List<TestCaseDO> addlist = new ArrayList<>();
          addlist.add(testCaseDO);
          log.info("插入数据返回状态码：{}", testCaseService.insertTestCaseAll(addlist));
          newTestCaseDO = addlist.get(0);
          newTestCaseDO.setTestCaseId("C" + addlist.get(0).getId());
          newTestCaseDO.setDefineId(newDefineId);
          newTestCaseDO.setName( newTestCaseDO.getName() + "_"+index);
          log.debug(newTestCaseDO.toString());
          log.info("更新testCaseId返回状态码：{}", testCaseService.updateTestCaseID(newTestCaseDO));
      }catch (Exception e){
          log.info("复制测试用例数据发生异常：{}",e );
          TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      }
    }

    public void amendStepParm(List<TestCaseDefineApiAutoStepDO> testCaseDefineApiAutoStepDOList,
                              Long newDefineId){

        for (TestCaseDefineApiAutoStepDO tstCaseDefineApiAutoStep : testCaseDefineApiAutoStepDOList) {
            tstCaseDefineApiAutoStep.setDefineId(newDefineId);
        }

    }

    @Override
    public void run() {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        try {
            //  Thread.sleep(300);
            copyTestCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
