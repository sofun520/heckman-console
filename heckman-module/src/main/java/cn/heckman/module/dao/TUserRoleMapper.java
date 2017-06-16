package cn.heckman.module.dao;

import java.util.Map;

import cn.heckman.framework.common.BaseMapper;
import cn.heckman.module.pojo.TUserRole;

public interface TUserRoleMapper extends BaseMapper<TUserRole> {

	void deleteUserRole(Map<String, Object> map);

	void saveUserRole(Map<String, Object> map);
}