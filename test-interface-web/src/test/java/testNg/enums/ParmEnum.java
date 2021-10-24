package testNg.enums;


import com.alibaba.dubbo.common.utils.StringUtils;
import lombok.Getter;

/**
     * 参数枚举类型
     *
     */
    @Getter
    public enum ParmEnum {


    /**
     * 参数异常
     */
    Key("Key", "Value"),


    /**
     * 参数异常
     */
    Hello("Hello", "123");

        private String code;

        private String message;

        ParmEnum(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static ParmEnum getByCode(String code) {
            if (StringUtils.isEmpty(code)) {
                return null;
            }
            for (ParmEnum parmEnum : ParmEnum.values()) {
                if (parmEnum.getCode().equals(code)) {
                    return parmEnum;
                }
            }
            return null;
        }
    }

