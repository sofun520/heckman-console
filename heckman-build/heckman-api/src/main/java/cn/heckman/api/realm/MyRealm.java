package cn.heckman.api.realm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.heckman.framework.utils.ShiroSessionUtil;
import cn.heckman.module.pojo.TPermission;
import cn.heckman.module.pojo.TRole;
import cn.heckman.module.pojo.TUser;
import cn.heckman.module.service.TUserService;

import com.alibaba.fastjson.JSONObject;

public class MyRealm extends AuthorizingRealm {

	@Autowired
	private TUserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
		String loginName = (String) principals.fromRealm(getName()).iterator()
				.next();

		TUser user = userService.getRolesByUsername(loginName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uUsername", user.getuUsername());
		List<TPermission> pers = userService.getPermissions(map);

		Set<String> roleNames = new HashSet<String>();
		Set<String> permissions = new HashSet<String>();
		List<TRole> roles = user.getRoles();
		for (TRole role : roles) {
			roleNames.add(role.getrName());// 添加角色
		}
		for (TPermission per : pers) {
			permissions.add(per.getpToken()); // 添加权限
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
		info.setStringPermissions(permissions);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uUsername", token.getUsername());
		List<TUser> list = userService.query(map);
		if (list.size() > 0) {
			TUser user = list.get(0);
			System.out.println("==========" + JSONObject.toJSONString(user));
			System.out.println("==========token"
					+ JSONObject.toJSONString(token));
			if ("2".equals(user.getuStatus())) {
				throw new LockedAccountException();
			} else if ("0".equals(user.getuStatus())) {
				if (token.getUsername().equals(user.getuUsername())) {
					ShiroSessionUtil.setSession(
							ShiroSessionUtil.USER_SESSION_NAME, user);
					return new SimpleAuthenticationInfo(user.getuUsername(),
							user.getuPassword(), getName());
				} else {
					throw new AuthenticationException();
				}
			} else if ("1".equals(user.getuStatus())) {
				throw new UnknownAccountException();
			}
		} else {
			throw new UnknownAccountException();
		}
		return null;
	}

}
