package cn.heckman.module.dao;

import java.util.List;
import java.util.Map;

import cn.heckman.framework.common.BaseMapper;
import cn.heckman.module.pojo.RolePermissionTree;
import cn.heckman.module.pojo.TPermission;

public interface TPermissionMapper extends BaseMapper<TPermission> {

	List<RolePermissionTree> userPermissionsTree(Map<String, Object> map);

	List<TPermission> permissionSetList(Map<String, Object> map);

}