package com.yoxwei.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.yoxwei.constant.CommonConstants;
import com.yoxwei.domain.SysUser;
import com.yoxwei.mapper.SysUserMapper;
import com.yoxwei.service.SysUserService;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper userMapper;

    public SysUserServiceImpl(SysUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public SysUser selectUserByUsername(String username) {
        SysUser user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new RuntimeException("未找到该用户");
        }
        if (CommonConstants.DISABLE.equals(user.getStatus())) {
            throw new RuntimeException("该用户已停用，请联系管理员");
        }
        return user;
    }

    @Override
    public SysUser selectUserByUserId(Long userId) {
        SysUser user = userMapper.selectByUserId(userId);
        if (user == null) {
            throw new RuntimeException("未找到该用户");
        }
        if (CommonConstants.DISABLE.equals(user.getStatus())) {
            throw new RuntimeException("该用户已停用，请联系管理员");
        }
        return user;
    }

    @Override
    public List<SysUser> getAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public Long addUser(SysUser user) {
        validation(user);
        user.setStatus(CommonConstants.ENABLE);
        user.setDelFlag(CommonConstants.NOT_DELETED);
        userMapper.insert(user);
        return user.getUserId();
    }

    @Override
    public void updateUser(SysUser user) {
        if (user == null || ObjectUtil.isEmpty(user.getUserId())) {
            throw new RuntimeException("用户ID不能为空");
        }
        SysUser user1 = userMapper.selectByUserId(user.getUserId());
        if (ObjectUtil.isEmpty(user1)) {
            throw new RuntimeException("未找到该用户，请刷新后重试");
        }
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Long userId) {
        if (ObjectUtil.isEmpty(userId)) {
            throw new RuntimeException("用户ID不能为空");
        }
        SysUser user = userMapper.selectByUserId(userId);
        if (ObjectUtil.isEmpty(user)) {
            throw new RuntimeException("未找到该用户，请刷新后重试");
        }
        SysUser update = new SysUser();
        update.setUserId(userId);
        update.setDelFlag(CommonConstants.DELETED);
        userMapper.updateUser(update);
    }

    private void validation(SysUser user) {
        if (ObjectUtil.isEmpty(user)) {
            throw new RuntimeException("用户信息为空");
        }
        if (StrUtil.isBlank(user.getUsername())) {
            throw new RuntimeException("用户名不能为空");
        }
        if (StrUtil.isBlank(user.getPassword())) {
            throw new RuntimeException("用户密码不能为空");
        }
    }


}
