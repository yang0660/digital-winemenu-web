package com.myicellar.digitalmenu.controller;

import com.myicellar.digitalmenu.vo.response.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 异常处理
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    private final Pattern DUPLICATE_PATTERN = Pattern.compile(".*Duplicate entry '(.*)' for key '(.*)'.*", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    private final Pattern CANNOT_BE_NULL_PATTERN = Pattern.compile(".*Column '(.*)' cannot be null.*", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    @ExceptionHandler
    @ResponseBody
    public ResultVO<?> handle(HandlerMethod handlerMethod, Exception e) {
        log.error("接口\"" + handlerMethod.getBeanType().getName() + "." + handlerMethod.getMethod().getName() + "\"异常", e);

        ResultVO<?> resultVO = ResultVO.unknowError(e);
        if(e instanceof DuplicateKeyException) {
            Matcher matcher = DUPLICATE_PATTERN.matcher(e.getMessage());
            if(matcher.matches()) {
                String value = matcher.group(1);
                String uniqIdx = matcher.group(2);
                return resultVO.setMessage(getUniqDesc(uniqIdx) + "'" + value + "'重复， 请修改后重试");
            }
            return resultVO.setMessage("数据有重复，请检查参数");
        } else if (e instanceof DataIntegrityViolationException) {
            Matcher matcher = CANNOT_BE_NULL_PATTERN.matcher(e.getMessage());
            if(matcher.matches()) {
                String column = matcher.group(1);
                return resultVO.setMessage("参数'" + getColumnDesc(column) + "'不能为空， 请设值");
            }
            return resultVO.setMessage("数据缺失，请检查参数");
        }

        return resultVO;
    }

    private String getColumnDesc(String column) {
        switch (column) {
            case "department_id": return "部门ID";
            case "copy_user_ids": return "抄送人";
            case "contract_id": return "合同ID";
            case "category_id": return "合同类型";
            case "company_number": return "单位编号";
            case "fax": return "传真号码";
            case "phone": return "固定电话";
            case "user_type": return "用户类型";
        }
        return column;
    }

    private String getUniqDesc(String uniqIdx) {
        switch (uniqIdx) {
            case "uniq_idx_contract_number": return "合同编号";
            case "uniq_idx_company_number": return "企业编号";
            case "uniq_idx_mobile": return "手机号码";
            default: return "数据";
        }
    }
}
