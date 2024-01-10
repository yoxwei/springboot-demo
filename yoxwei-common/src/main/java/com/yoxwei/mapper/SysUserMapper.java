package com.yoxwei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yoxwei.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户名称查找用户
     */
    SysUser selectByUsername(@Param("username") String username);

    /**
     * 根据用户ID查找用户
     */
    SysUser selectByUserId(Long userId);

    /**
     * 查询所有用户
     */
    List<SysUser> selectAllUser();

    /**
     * 更新用户
     */
    void updateUser(SysUser user);
}
