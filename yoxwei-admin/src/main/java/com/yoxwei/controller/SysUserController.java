package com.yoxwei.controller;

import com.yoxwei.domain.Result;
import com.yoxwei.domain.SysUser;
import com.yoxwei.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户接口
 * @author yoxwei
 */
@Getter
@RestController
@Api(tags = "系统用户接口")
public class SysUserController {

    private final SysUserService userService;

    public SysUserController(SysUserService userService) {
        this.userService = userService;
    }

    @ApiOperation("获取所有用户信息")
    @GetMapping("/list")
    public Result<List<SysUser>> getAll() {
        List<SysUser> userList = userService.getAllUser();
        return Result.success(userList);
    }

    @ApiOperation("根据用户名称查找用户")
    @GetMapping("/getByName")
    public Result<SysUser> getByName(@RequestParam(value = "username") String username) {
        SysUser user = userService.selectUserByUsername(username);
        return Result.success(user);
    }

    @ApiOperation("根据用户ID查找用户")
    @GetMapping("/getById")
    public Result<SysUser> getById(@RequestParam(value = "userId") Long userId) {
        SysUser user = userService.selectUserByUserId(userId);
        return Result.success(user);
    }

    @ApiOperation("添加新用户")
    @PostMapping("/addUser")
    public Result<Long> addUser(@RequestBody SysUser user) {
        Long userId = userService.addUser(user);
        return Result.success(userId);
    }

    @ApiOperation("更新用户")
    @PostMapping("/updateUser")
    public Result<Void> updateUser(@RequestBody SysUser user) {
        userService.updateUser(user);
        return Result.success();
    }

    @ApiOperation("删除用户")
    @PostMapping("/deleteUser")
    public Result<Void> deleteUser(Long userId) {
        userService.deleteUser(userId);
        return Result.success();
    }
}
