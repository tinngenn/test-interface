package com.example.test.dal.mapper;


import com.example.test.dal.moduleDO.TestCaseDO;
import com.example.test.dal.moduleDO.TestCaseDefineApiAutoDO;
import com.example.test.dal.moduleDO.TestCaseDefineApiAutoStepDO;
import lombok.extern.slf4j.Slf4j;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;


@Slf4j
public class InsetAllProvider {

    // 插入测试用例表
    public String insertTestCaseAll(Map map) {
        List<TestCaseDO> list = (List<TestCaseDO>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("insert into test_case");
        sb.append("(id,tenant_id,name,created_at,modified_at,created_by,modified_by," +
                "library_id,test_case_code,test_case_type,priority,tree_path) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(null," +
                "#'{'list[{0}].tenantId}," +
                "#'{'list[{0}].name}," +
                "#'{'list[{0}].createdAt}," +
                "#'{'list[{0}].modifiedAt}," +
                "#'{'list[{0}].createdBy}," +
                "#'{'list[{0}].modifiedBy}," +
                "#'{'list[{0}].libraryId}," +
                "#'{'list[{0}].testCaseCode}," +
                "#'{'list[{0}].testCaseType}," +
                "#'{'list[{0}].priority}," +
                "#'{'list[{0}].treePath})");
        for (int i = 0; i < list.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        log.debug("用例表成功入库,SQL:{}" , sb.toString());
        return sb.toString();
    }





    // 插入测试用例表配置表
    public String insertTestCaseDefineApiAutoAll(Map map) {
        List<TestCaseDefineApiAutoDO> list = (List<TestCaseDefineApiAutoDO>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("insert into test_case_define_api_auto ");
        sb.append("(id,tenant_id, created_at, modified_at) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(" +
                "#'{'list[{0}].id}," +
                "#'{'list[{0}].tenantId}," +
                "#'{'list[{0}].createdAt}," +
                "#'{'list[{0}].modifiedAt})");
        for (int i = 0; i < list.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        log.debug("用例自动表成功入库,SQL:{}" , sb.toString());
        return sb.toString();
    }


    // 插入测试用例表配置表
    public String insertTestCaseDefineApiAutoStepAll(Map map) {
        List<TestCaseDefineApiAutoStepDO> list = (List<TestCaseDefineApiAutoStepDO>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("insert into test_case_define_api_auto_step");
        sb.append("(id,tenant_id,created_at,modified_at,define_id,type,name,parent_id,`index`,struct) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(null," +
                "#'{'list[{0}].tenantId}," +
                "#'{'list[{0}].createdAt}," +
                "#'{'list[{0}].modifiedAt}," +
                "#'{'list[{0}].defineId}," +
                "#'{'list[{0}].type}," +
                "#'{'list[{0}].name}," +
                "#'{'list[{0}].parentId}," +
                "#'{'list[{0}].index}," +
                "#'{'list[{0}].struct})");
        for (int i = 0; i < list.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        log.debug("用例配置成功入库,SQL:{}" , sb.toString());
        return sb.toString();
    }


}
