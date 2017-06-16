package cn.heckman.framework.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class ShiroSessionUtil {

	public static String USER_SESSION_NAME = "userinfo";

	/**
	 * shiro保存值session
	 * 
	 * @param key
	 * @param value
	 */
	public static void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

	/**
	 * 获取shiro中session值
	 * 
	 * @param key
	 * @return
	 */
	public static Object getSession(Object key) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				return session.getAttribute(key);
			}
		}
		return null;
	}

}
