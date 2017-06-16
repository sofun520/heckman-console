package cn.heckman.framework.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	public static String ADMIN = "admin";
	public static String SYS_ADMIN = "sys_admin";

	/* 权限菜单标识 */
	public static String PERMISSION_MENU_TYPE = "1";
	/* 权限操作标识 */
	public static String PERMISSION_OPERATE_TYPE = "2";

	/* 未知账户 */
	public static String UNKOWN_ACCOUNT = "10000001";
	/* 错误的凭证 */
	public static String INCORRECT_CREDENTIALS = "10000002";
	/* 账户已锁定 */
	public static String LOCKED_ACCOUNT = "10000003";
	/* 错误次数过多 */
	public static String EXCESSIVE_ATTEMPS = "10000004";
	/* 用户名或密码不正确 */
	public static String AUTHENTICATION = "10000005";
	/* 错误的验证码 */
	public static String INCORRECT_VERIFYCODE = "10000006";

	public static String INNER_ERROR = "10000500";

	/* 错误参数 */
	public static String INCORRECT_PARAM = "10000007";

	public static String SUCCESS = "0";

	public static String FAILED = "1";

	public static String getErrMsg(String errorCode) {
		Map<String, String> errMap = new HashMap<String, String>();
		errMap.put("10000001", "该用户不存在");
		errMap.put("10000002", "用户名或密码不正确");
		errMap.put("10000003", "用户已被锁定");
		errMap.put("10000004", "错误次数过多");
		errMap.put("10000005", "用户名或密码不正确");
		errMap.put("10000006", "错误的验证码");
		errMap.put("10000500", "系统内部错误");
		errMap.put("10100000", "新增成功");
		errMap.put("10200000", "更新成功");
		errMap.put("10300000", "删除成功");
		errMap.put("10000007", "参数异常");
		return errMap.get(errorCode);
	}

	public static String INSERT_SUCCESS = "10001001";
	public static String UPDATE_SUCCESS = "10001002";
	public static String DELETE_SUCCESS = "10001003";
	public static String LOGOUT_SUCCESS = "10001004";
	public static String QUERY_NULL = "10001005";
	public static String QUERY_SUCCESS = "10001006";

	public static String getSuccessMsg(String errorCode) {
		Map<String, String> sucMap = new HashMap<String, String>();
		sucMap.put("10001001", "新增成功");
		sucMap.put("10001002", "更新成功");
		sucMap.put("10001003", "删除成功");
		sucMap.put("10001004", "注销成功");
		sucMap.put("10001005", "未查询到数据");
		sucMap.put("10001006", "查询成功");
		return sucMap.get(errorCode);
	}
}
