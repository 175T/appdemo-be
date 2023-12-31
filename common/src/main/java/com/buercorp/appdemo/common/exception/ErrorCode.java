package com.buercorp.appdemo.common.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/**
 * @description 异常信息枚举
 *
 * @author tanghx
 * @date 2023/12/4 10:00
 */
@Getter
@NoArgsConstructor
public enum ErrorCode {

    SUCCESS(HttpStatus.OK.value(),200),

    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(),400),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(),401),

    FORBIDDEN(HttpStatus.FORBIDDEN.value(),403),

    NOT_FOUND(HttpStatus.NOT_FOUND.value(),404),

    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(),405),

    CONFLICT(HttpStatus.CONFLICT.value(), 409),

    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),415),

    NODE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500),

    LOGIN_ERROR_PASSWORD(HttpStatus.BAD_REQUEST.value(),1000),

    LOGIN_ERROR_USERNAME(HttpStatus.BAD_REQUEST.value(),1001),

    LOGIN_AUTH(HttpStatus.INTERNAL_SERVER_ERROR.value(), 1002),

    ACCOUNT_STOP(HttpStatus.INTERNAL_SERVER_ERROR.value(), 1003),

    USER_DOSE_NOT_EXIST(HttpStatus.BAD_REQUEST.value(), 1004),

    UPDATE_PASSWORD_NEW_PASSWORD_CONFIRM_NOT_MATCH(HttpStatus.BAD_REQUEST.value(),1009),

    VALIDATE_CODE_ERROR(HttpStatus.BAD_REQUEST.value(),1011) ,

    UPDATE_PASSWORD_NEW_PASSWORD_LENGTH_NOT_OK(HttpStatus.BAD_REQUEST.value(),1010),

    USER_REPEAT(HttpStatus.BAD_REQUEST.value(), 1013);

    private int httpStatus;         // http 响应码

    private int appCode;            // 自定义响应码

    // message key
    private String i18nKey;         // 国际化信息-键

    ErrorCode(int httpStatus, int appCode) {
        this.httpStatus = httpStatus;
        this.appCode = appCode;
    }

    private ErrorCode(int httpStatus, int appCode, String i18nKey) {
        this.httpStatus = httpStatus;
        this.appCode = appCode;
        this.i18nKey = i18nKey;
    }
}
