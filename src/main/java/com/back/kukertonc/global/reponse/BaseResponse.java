package com.back.kukertonc.global.reponse;

import com.back.kukertonc.global.reponse.status.BaseExceptionStatus;
import com.back.kukertonc.global.reponse.status.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;


@Getter
@JsonPropertyOrder({"code", "status", "message", "result"})
public class BaseResponse<T> implements ResponseStatus {

    private final int code;
    private final int status;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T result;

    public BaseResponse(T result) {
        this.code = BaseExceptionStatus.SUCCESS.getCode();
        this.status = BaseExceptionStatus.SUCCESS.getStatus();
        this.message = BaseExceptionStatus.SUCCESS.getMessage();
        this.result = result;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
