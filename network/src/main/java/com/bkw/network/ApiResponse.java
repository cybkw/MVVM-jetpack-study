package com.bkw.network;

public class ApiResponse<T> {
    public T data;
    public int errorCode;
    public String errorMsg;

    public ApiResponse() {
    }

    public ApiResponse(T data, int errorCode, String errorMsg) {
        this.data = data;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
