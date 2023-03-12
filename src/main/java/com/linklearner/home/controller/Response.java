package com.linklearner.home.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;

public class Response {


    private Object data;
    private String message;

    private STATUS_CODE statusCode;

    private Response() {
    }

    public static Response success(HttpServletResponse httpServletResponse, Object data) {
        return success(httpServletResponse, data, null);
    }

    public static Response success(HttpServletResponse httpServletResponse, Object data, String message) {
        Response response = new Response();
        response.setStatusCode(STATUS_CODE.SUCCESS);
        response.setData(data);
        response.setMessage(StringUtils.isBlank(message) ? "success" : message);
        return response;
    }

    public static Response error(HttpServletResponse httpServletResponse, STATUS_CODE statusCode, String message) {
        Response response = new Response();
        httpServletResponse.setStatus(500);
        response.setStatusCode(statusCode == null ? STATUS_CODE.UNKNOWN_ERROR : statusCode);
        response.setData(Collections.EMPTY_MAP);
        response.setMessage(StringUtils.isBlank(message) ? "error" : message);
        return response;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode.value;
    }

    public void setStatusCode(STATUS_CODE statusCode) {
        this.statusCode = statusCode;
    }

    enum STATUS_CODE {
        SUCCESS(200),
        UNKNOWN_ERROR(5000),
        BAD_REQUEST(5001);

        private final int value;

        STATUS_CODE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
