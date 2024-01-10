package com.yoxwei.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "通用返回")
public class Result<S> {

    @ApiModelProperty(value = "状态码")
    private int code;

    @ApiModelProperty(value = "提示消息")
    private String msg;

    @ApiModelProperty(value = "返回数据")
    private S data;

    @ApiModelProperty(value = "时间戳")
    private long timestamp;

    private Result(int code, String msg, S data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public static <S> Result<S> success(String msg, S data) {
        return new Result<>(200, msg, data);
    }

    public static <S> Result<S> success(S data) {
        return success("操作成功", data);
    }

    public static <S> Result<S> success() {
        return success(null);
    }

    public static <S> Result<S> error(int code, String msg, S data) {
        return new Result<>(code, msg, data);
    }

    public static <S> Result<S> error(int code, String msg) {
        return error(code, msg, null);
    }
}
