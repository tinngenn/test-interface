package com.example.test.dal.mapper;

import com.example.test.dal.moduleDO.TestCaseDefineApiAutoDO;
import com.example.test.dal.moduleDO.TestCaseDefineApiAutoStepDO;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface TestCaseDefineApiAutoDOMapper {

    @Select("select * from test_case_define_api_auto  where id = #{tenantId}")
    @Results(value = { @Result(id = true, column = "id", property = "id")})
     List<TestCaseDefineApiAutoDO> getTestCaseDefineApiAutoDO(@Param("tenantId") Long tenantId);


    // 插入test_case_define_api_auto测试用例配置表
    @InsertProvider(type = InsetAllProvider.class, method = "insertTestCaseDefineApiAutoAll")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertTestCaseDefineApiAutoAll(@Param("list") List<TestCaseDefineApiAutoDO> list);




}
