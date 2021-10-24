package com.example.test.dal.mapper;

import com.example.test.dal.moduleDO.TestCaseDO;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface TestCaseDOMapper {

    @Select("select * from test_case  where  test_case_id = #{testCaseId}")
    @Results(value = { @Result(id = true, column = "id", property = "id")})
     List<TestCaseDO> getTestCase(@Param("testCaseId") String testCaseId);


    // 插入Test_casec测试用例表
    @InsertProvider(type = InsetAllProvider.class, method = "insertTestCaseAll")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertTestCaseAll(@Param("list") List<TestCaseDO> list);



    // 更新用例编号和id号相同并加C前缀
    @Update({ "update test_case set test_case_id = #{testCaseId}," +
            "tenant_id = #{tenantId}," +
            "name = #{name}," +
            "created_at = #{createdAt}," +
            "modified_at = #{modifiedAt}," +
            "created_by = #{createdBy}," +
            "modified_by = #{modifiedBy}," +
            "library_id = #{libraryId}," +
            "test_case_type = #{testCaseType}," +
            "priority = #{priority}," +
            "define_id = #{defineId}," +
            "tree_path = #{treePath}" +
            "  where id = #{id}" })
     int updateTestCaseID(TestCaseDO testCaseDO);

}
