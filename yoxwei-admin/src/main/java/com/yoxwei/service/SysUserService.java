package com.yoxwei.service;

import com.yoxwei.domain.SysUser;

import java.util.List;

public interface SysUserService {

    /**
     * 根据用户名查找用户
     */
    SysUser selectUserByUsername(String username);

    /**
     * 根据用户ID查找用户
     */
    SysUser selectUserByUserId(Long userId);

    /**
     * 获取所用用户
     */
    List<SysUser> getAllUser();

    /**
     * 添加新用户
     */
    Long addUser(SysUser user);

    /**
     * 更新用户
     */
    void updateUser(SysUser user);

    /**
     * 删除用户
     */
    void deleteUser(Long userId);
}
