package com.dawn.ych;

public class ResponseStatus {
    protected String ErrorCode;//错误码
    protected String Message;//错误信息

    public String getErrorCode() {
        return ErrorCode;
    }

    public String getMessage() {
        return Message;
    }

    @Override
    public String toString() {
        return "ResponseStatus{" +
                "ErrorCode='" + ErrorCode + '\'' +
                ", Message='" + Message + '\'' +
                '}';
    }
}
