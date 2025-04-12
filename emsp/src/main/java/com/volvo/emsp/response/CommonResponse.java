package com.volvo.emsp.response;

import com.volvo.application.model.ResponseType;
import com.lenovo.iop.user.auth.common.MessageUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    @ApiModelProperty(value = "服务器返回状态码", name = "code", example = "200", required = true)
    private Integer code;

    @ApiModelProperty(value = "服务状态码中文说明", name = "message", example = "成功", required = true)
    private String message;
    private T result;

    public static <T> CommonResponse<T> success(T result) {
        return CommonResponse.<T>builder()
                .code(ResponseType.SUCCESS.getCode())
                .message(MessageUtils.get(ResponseType.SUCCESS.name()))
                .result(result)
                .build();
    }

    public static <T> CommonResponse<T> by(ResponseType responseType) {
        return CommonResponse.<T>builder()
                .code(responseType.getCode())
                .message(MessageUtils.get(responseType.name()))
                .build();
    }

    public static <T> CommonResponse<T> by(ResponseType responseType, String msg) {
        return CommonResponse.<T>builder()
                .code(responseType.getCode())
                .message(MessageUtils.get(responseType.name(),msg))
                .build();
    }

    public static <T> CommonResponse<T> by(ResponseType responseType, T result) {
        return CommonResponse.<T>builder()
                .code(responseType.getCode())
                .message(MessageUtils.get(responseType.name()))
                .result(result)
                .build();
    }
}
