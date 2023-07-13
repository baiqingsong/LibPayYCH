package com.dawn.ych;

public class BaseResModel {
    protected ResponseStatus ResponseStatus;

    public ResponseStatus getResponseStatus() {
        return ResponseStatus;
    }

    @Override
    public String toString() {
        return "BaseResModel{" +
                "ResponseStatus=" + ResponseStatus +
                '}';
    }
}
