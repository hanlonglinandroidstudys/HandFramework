
/*
 * Copyright (c) 2019/3/19
 * Create at 4:9:20
 * Wriitten by hanlonglin
 */

package hanlonglin.com.httpframework.bean;

import com.alibaba.fastjson.JSON;

public class RequestData {

    /**
     * {"resultcode":"101","reason":"错误的请求KEY","result":null,"error_code":10001}
     */
    String resultcode;
    String reason;
    String result;
    String error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
