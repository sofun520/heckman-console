package cn.heckman.module.dao;

import java.util.List;
import java.util.Map;

import cn.heckman.framework.common.BaseMapper;
import cn.heckman.module.pojo.TPermission;
import cn.heckman.module.pojo.TUser;
import cn.heckman.module.pojo.UserRoleTree;

public interface TUserMapper extends BaseMapper<TUser> {

	TUser getRolesByUsername(String username);

	List<TPermission> getPermissions(Map<String, Object> map);

	List<TUser> queryPage(Map<String, Object> map);

	List<UserRoleTree> userRoleTree(Map<String, Object> map);

	TUser findUser(TUser user);

}