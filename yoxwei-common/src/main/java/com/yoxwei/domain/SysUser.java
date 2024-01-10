package com.yoxwei.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("sys_user")
@ApiModel(value = "系统用户")
public class SysUser {

    @TableId
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "删除状态，1未删除 0已删除")
    private String delFlag;

    @ApiModelProperty(value = "启用状态，1启用，0停用")
    private String status;
}
