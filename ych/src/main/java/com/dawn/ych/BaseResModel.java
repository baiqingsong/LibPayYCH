package com.dawn.ych;

import androidx.annotation.NonNull;

class BaseResModel {
    protected ResponseStatus ResponseStatus;

    public ResponseStatus getResponseStatus() {
        return ResponseStatus;
    }

    @NonNull
    @Override
    public String toString() {
        return "BaseResModel{" +
                "ResponseStatus=" + ResponseStatus +
                '}';
    }
}
