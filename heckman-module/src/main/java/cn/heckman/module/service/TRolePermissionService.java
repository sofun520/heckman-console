package cn.heckman.module.service;

import java.util.Map;

import cn.heckman.framework.common.BaseService;
import cn.heckman.module.pojo.TRolePermission;

public interface TRolePermissionService extends BaseService<TRolePermission> {

	void saveRolePermissions(Map<String, Object> map);

}
