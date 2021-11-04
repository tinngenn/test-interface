package com.example.test.common;

/**
 *  使用常量
 *
 * @author shenyuan
 * @version v0.1 2020/07/06 16:17
 */
public class Constants {
    /* 日志 MDC名 */
    public static final String TRACE_LOG_ID = "TRACE_LOG_ID";
    public static final String dstTraceId = "dstTraceId";
    public static final String UNDERLINE = "_";
    public static final String TEST_TPOIC = "testtopic";

    public static final String TOCEAN_INFO_EVENT = "tocean_infoEvent";
    public static final String TOCEAN_TIMED_EVENT = "tocean_timedEvent";


    public static final String InfoEventKafka = "InfoEventKafka";

    /* kafka等待监控时间*/
    public static final String KafkaListenerTime = "10000";
    /* 查询数据库等待时间*/
    public static final String dataDBListenerTime = "10000";

    public static final String TEST_CASE_ID = "TEST_CASE_ID";
    public static final String UNKNOWN = "UNKNOWN";
    public static final String REFUNDNAME = "退款_";
    public static final String REFUNTEXCELFILE = "RefundDate.xlsx";
    public static final String EXEXUTE = "执行";
    public static final String ACC_SUCCESS = "ACC_SUCCESS";
    public static final String SUCCESS = "SUCCESS";
    public static final String GW_DEAL = "GW_DEAL";
    public static final String TRADE_ACQUIRING = "TRADE_ACQUIRING";
    public static final String APP = "APP";
    public static final String CCY = "156";

    public static final String FREE = "FREE";
    public static final String FALSE = "FALSE";
    public static final String PRODUCT = "PRODUCT";
    public static final String DEFAULT = "DEFAULT";



    /* 商户注册 MERCHANT_TYPE 类型  */


    /* 序列名称 */

    public static final String SEQ_PHONE_NO = "SEQ_PHONE_NO";

    /* BO对账字段 */
    public static final String tradeNo = "tradeNo";
    public static final String testName = "testName";
    public static final String traceLogid = "traceLogid";
    public static final String payConsoleMessage = "payConsoleMessage";


    /* BO类名称 */
    public static final String QuickPayDataBO = "QuickPayDataBO";
    public static final String OuterBankPayDataBO = "OuterBankPayDataBO";
    public static final String RefundDataBO = "RefundDataBO";

    /* DO类名称 */
    public static final String payRegisterDO = "payRegisterDO";
    public static final String refundRegisterDO = "refundRegisterDO";
    public static final String tradeDetailDO = "tradeDetailDO";

    /* Redis Key 前缀 */
    public static final String PAY_SETTLEROUTE_TRADE_CHANNEL_INFO = "PAY_SETTLEROUTE_TRADE_CHANNEL_INFO";
    public static final String PAY_SETTLEROUTE_TRADE_CIF_INFO = "PAY_SETTLEROUTE_TRADE_CIF_INFO";
    public static final String SETTLECORE_MERCHANT_INFO = "SETTLECORE_MERCHANT_INFO";




    /* sheet名 */
    public static final String TradeProductRefundReqDTO = "TradeProductRefundReqDTO";


    /* 退款Excel 字段 */
    public static final String NoOrOFF = "NoOrOFF";
    public static final String Desired = "Desired";
    public static final String refundDesired = "refundDesired";
    public static final String requestOrderNo = "requestOrderNo";
    public static final String requestDate = "requestDate";
    public static final String requestSystem = "requestSystem";
    public static final String refundTradeChannel = "refundTradeChannel";
    public static final String merchantNo = "merchantNo";
    public static final String originalTradeNo = "originalTradeNo";
    public static final String stockMerchantOrderNo = "stockMerchantOrderNo";
    public static final String merchantTradeNo = "merchantTradeNo";
    public static final String refundAmount = "refundAmount";
    public static final String refundCcy = "refundCcy";
    public static final String refundReason = "refundReason";
    public static final String productCode = "productCode";
    public static final String operator = "operator";
      /* 退款Excel 字段值 */



    /*转换 自动生成 字段*/
    public static final String REQUESTORDERNO  = "REQUESTORDERNO";

    public static final String REQUESTDATE  = "REQUESTDATE";

    public static final String TRACELOGID  = "TRACELOGID";

    public static final String OPERATOR  = "OPERATOR";

    /*old kafka topic*/
    public static final String PUREPAY_ORDERSTATUS_CALLBACK = "PUREPAY_ORDERSTATUS_CALLBACK";
    public static final String BANK_CALLBACK_INTEGRATIONPAY = "BANK_CALLBACK_INTEGRATIONPAY";

//

    /** 默认日期时间格式 */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /** 默认日期格式 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /** 默认时间格式 */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";


    public static final String STEP_TIMEOUT = "{\n" +
            "\t\t\"id\": \"6e475296-8404-4fcc-a62b-d34baec67305\",\n" +
            "\t\t\"type\": 1,\n" +
            "\t\t\"@type\": \"1\",\n" +
            "\t\t\"protocol\": \"http\",\n" +
            "\t\t\"hostname\": \"10.10.220.36\",\n" +
            "\t\t\"port\": \"8092\",\n" +
            "\t\t\"followEnv\": false,\n" +
            "\t\t\"izUseEnv\": false,\n" +
            "\t\t\"serverId\": null,\n" +
            "\t\t\"headers\": [],\n" +
            "\t\t\"params\": [{\n" +
            "\t\t\t\"key\": \"timeout\",\n" +
            "\t\t\t\"value\": \"1111\"\n" +
            "\t\t}],\n" +
            "\t\t\"paramsString\": \"timeout:1111\",\n" +
            "\t\t\"method\": \"POST\",\n" +
            "\t\t\"path\": \"timeout\",\n" +
            "\t\t\"pathInfo\": [],\n" +
            "\t\t\"form\": [],\n" +
            "\t\t\"formData\": [],\n" +
            "\t\t\"preScript\": [{\n" +
            "\t\t\t\"type\": 1,\n" +
            "\t\t\t\"script\": \"\"\n" +
            "\t\t}],\n" +
            "\t\t\"postScript\": [{\n" +
            "\t\t\t\"type\": 1,\n" +
            "\t\t\t\"script\": \"\"\n" +
            "\t\t}],\n" +
            "\t\t\"assignment\": [],\n" +
            "\t\t\"assert\": [],\n" +
            "\t\t\"config\": {\n" +
            "\t\t\t\"timeout\": 5000,\n" +
            "\t\t\t\"followRedirect\": true,\n" +
            "\t\t\t\"maxRedirects\": 10,\n" +
            "\t\t\t\"methodRewriting\": true,\n" +
            "\t\t\t\"http2\": false,\n" +
            "\t\t\t\"retry\": 1,\n" +
            "\t\t\t\"rejectUnauthorized\": true\n" +
            "\t\t}\n" +
            "\t}";
}
