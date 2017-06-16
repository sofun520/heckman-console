package cn.heckman.module.common;

public class OperateLogConstants {

	public static String PERMISSION_QUERY_OPTION = "100101";
	public static String PERMISSION_DELETE_OPTION = "100102";
	public static String PERMISSION_UPDATE_OPTION = "100103";
	public static String PERMISSION_INSERT_OPTION = "100104";

	public static String ROLE_QUERY_OPTION = "100201";
	public static String ROLE_DELETE_OPTION = "100202";
	public static String ROLE_UPDATE_OPTION = "100203";
	public static String ROLE_INSERT_OPTION = "100204";

	public static String USER_QUERY_OPTION = "100301";
	public static String USER_DELETE_OPTION = "100302";
	public static String USER_UPDATE_OPTION = "100303";
	public static String USER_INSERT_OPTION = "100304";

	public static String getOperateLogDes(String code) {
		StringBuffer a = new StringBuffer("");
		if ("100101".equals(code)) {
			a = new StringBuffer("查询权限信息");
		} else if ("100102".equals(code)) {
			a = new StringBuffer("删除权限信息");
		} else if ("100103".equals(code)) {
			a = new StringBuffer("修改权限信息");
		} else if ("100104".equals(code)) {
			a = new StringBuffer("新增权限信息");
		} else if ("100201".equals(code)) {
			a = new StringBuffer("查询角色信息");
		} else if ("100202".equals(code)) {
			a = new StringBuffer("删除角色信息");
		} else if ("100203".equals(code)) {
			a = new StringBuffer("修改角色信息");
		} else if ("100204".equals(code)) {
			a = new StringBuffer("新增角色信息");
		} else if ("100301".equals(code)) {
			a = new StringBuffer("查询用户信息");
		} else if ("100302".equals(code)) {
			a = new StringBuffer("删除用户信息");
		} else if ("100303".equals(code)) {
			a = new StringBuffer("修改用户信息");
		} else if ("100304".equals(code)) {
			a = new StringBuffer("新增用户信息");
		}
		return a.toString();
	}

}
