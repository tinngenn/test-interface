package com.example.test.dal.mapper;

import com.example.test.dal.moduleDO.TestCaseDO;
import com.example.test.dal.moduleDO.TestCaseDefineApiAutoStepDO;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface TestCaseDefineApiAutoStepDOMapper {

    @Select("select * from test_case_define_api_auto_step  where define_id = #{defineld}")
    @Results(value = { @Result(id = true, column = "id", property = "id")})
     List<TestCaseDefineApiAutoStepDO> getTestCaseDefineApiAutoStepDO(@Param("defineld") Long defineld);


    // 插入Test_casec测试用例表
    @InsertProvider(type = InsetAllProvider.class, method = "insertTestCaseDefineApiAutoStepAll")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertTestCaseDefineApiAutoStepAll(@Param("list") List<TestCaseDefineApiAutoStepDO> list);




}
