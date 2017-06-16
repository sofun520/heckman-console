package cn.heckman.module.service;

import java.util.List;
import java.util.Map;

import cn.heckman.framework.common.BaseService;
import cn.heckman.module.pojo.RolePermissionTree;
import cn.heckman.module.pojo.TPermission;

public interface TPermissionService extends BaseService<TPermission> {
	List<RolePermissionTree> userPermissionsTree(Map<String, Object> map);

	List<TPermission> permissionSetList(Map<String, Object> map);

}
