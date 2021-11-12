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
public class CopyTestCaseService implements Runnable {

    TestCaseService testCaseService;

    TestCaseDefineApiAutoStepService testCaseDefineApiAutoStepService;

    TestCaseDefineApiAutoService testCaseDefineApiAutoService;


    private String testCaseCode;
    private Integer index;

    public void setTestCaseCode(String testCaseId,Integer index) {

        this.testCaseCode = testCaseId;
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


        log.info("testCaseId:{}",testCaseCode);
       try {
          TestCaseDO testCaseDO;
          TestCaseDO newTestCaseDO;//插入后更新都最新自增id
          Long oldTestCodeId;
          Long newTestCodeId;//插入后更新都最新自增id
          //根据TestCaseCode查询出远用例，实际只有一条数据
          testCaseDO = testCaseService.getTestCase(testCaseCode).get(0);
           oldTestCodeId = testCaseDO.getId();
          log.info("查询用例表返回：{}", testCaseDO.toString());

           List<TestCaseDO> addlist = new ArrayList<>();
           //更新插入的用例名
           testCaseDO.setName( testCaseDO.getName() + "_"+ index);
           addlist.add(testCaseDO);
           //插入用例表生成新数据
           log.info("插入TestCase状态：{}", testCaseService.insertTestCaseAll(addlist));
           //获取的新的id自增ID号
           newTestCaseDO = addlist.get(0);
           newTestCodeId = newTestCaseDO.getId();
           log.info("newTestCodeId:{}", String.valueOf(newTestCodeId));
           newTestCaseDO.setTestCaseCode("C" + addlist.get(0).getId());
           log.info(newTestCaseDO.toString());
           log.info("更新TestCaseCode状态：{}", testCaseService.updateTestCaseCode(newTestCaseDO));
           //查询出用例明细表的数据，可能多条
           List<TestCaseDefineApiAutoStepDO> testCaseDefineApiAutoStepDOList
                   = testCaseDefineApiAutoStepService.getTestCaseDefineApiAutoStepDO(oldTestCodeId);
           if(testCaseDefineApiAutoStepDOList.size()<1){
               log.info("未配置步骤");
              return;
           }
           log.info("TestCaseDefineApiAutoStep查询结果前：{}", testCaseDefineApiAutoStepDOList.toString());
           //循环更新新的用例关联编号 DefineId
           amendStepDefineId(testCaseDefineApiAutoStepDOList,newTestCodeId);
           log.info("TestCaseDefineApiAutoStep查询结果后：{}", testCaseDefineApiAutoStepDOList.toString());

          //复制插入用例复制表
          log.info("TestCaseDefineApiAutoStep状态：{}",
                  testCaseDefineApiAutoStepService.insertTestCaseDefineApiAutoStepAll(testCaseDefineApiAutoStepDOList));
          log.info(testCaseDefineApiAutoStepDOList.toString());

           List<TestCaseDefineApiAutoDO> testCaseDefineApiAutoDOList =
                   testCaseDefineApiAutoService.getTestCaseDefineApiAutoDO(oldTestCodeId);

           TestCaseDefineApiAutoDO testCaseDefineApiAutoDO = testCaseDefineApiAutoDOList.get(0);
           testCaseDefineApiAutoDO.setId(newTestCaseDO.getId());
           List<TestCaseDefineApiAutoDO> newTestCaseDefineApiAutoDOlist = new ArrayList<>();
           newTestCaseDefineApiAutoDOlist.add(testCaseDefineApiAutoDO);
          log.info("插入TestCaseDefineApiAuto状态：{}",
                  testCaseDefineApiAutoService.insertTestCaseDefineApiAutoAll(newTestCaseDefineApiAutoDOlist));

      }catch (Exception e){
          log.info("复制测试用例数据发生异常：{}",e );
          TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      }
    }

    public void amendStepDefineId(List<TestCaseDefineApiAutoStepDO> testCaseDefineApiAutoStepDOList,
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
