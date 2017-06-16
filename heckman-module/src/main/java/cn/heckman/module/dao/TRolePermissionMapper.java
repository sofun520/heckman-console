package cn.heckman.module.dao;

import java.util.Map;

import cn.heckman.framework.common.BaseMapper;
import cn.heckman.module.pojo.TRolePermission;

public interface TRolePermissionMapper extends BaseMapper<TRolePermission> {

	void saveRolePermissions(Map<String, Object> map);

	void deleteRolePermissions(Map<String, Object> map);

}