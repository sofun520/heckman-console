package cn.heckman.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.heckman.framework.utils.Constants;
import cn.heckman.framework.utils.ResponseData;
import cn.heckman.module.pojo.RolePermissionTree;
import cn.heckman.module.pojo.TPermission;
import cn.heckman.module.pojo.TRole;
import cn.heckman.module.pojo.TUser;
import cn.heckman.module.service.TPermissionService;
import cn.heckman.module.service.TRolePermissionService;
import cn.heckman.module.service.TUserService;
import cn.heckman.module.vo.PermissionUser;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/permission")
public class TPermissionController {

	@Autowired
	private TUserService service;

	@Autowired
	private TPermissionService perService;

	@Autowired
	private TRolePermissionService rpService;

	private Logger logger = Logger.getLogger(TPermissionController.class);

	private static String PERMISSION_MENUS_TYPE = "1";
	private static String PERMISSION_PERS_TYPE = "2";

	/**
	 * 拉取用户菜单
	 * 
	 * @param permission
	 * @return
	 */
	@RequestMapping(value = "/getUserMenus", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData getUserMenus(@RequestBody PermissionUser permissionUser) {
		System.out.println(permissionUser);
		ResponseData responseData = new ResponseData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// TUser user = (TUser) ShiroSessionUtil.getSession("userinfo");
			map.put("uUsername", permissionUser.getuUsername());
			map.put("pType", permissionUser.getpType());
			map.put("flag", 0);
			map.put("pParent", permissionUser.getpParent());
			// map.put("roleId", permissionUser.getRoleId());
			List<TPermission> list = service.getPermissions(map);
			responseData.setData(list);
			responseData.setCode(Constants.SUCCESS);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return responseData;
	}

	/**
	 * 拉取用户permissions
	 * 
	 * @param permission
	 * @return
	 */
	@RequestMapping(value = "/getUserPermission", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData queryUserPermissionMenu(
			@RequestBody PermissionUser permissionUser) {
		ResponseData responseData = new ResponseData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// TUser user = (TUser) ShiroSessionUtil.getSession("userinfo");
			map.put("uUsername", permissionUser.getuUsername());
			// map.put("pType", PERMISSION_PERS_TYPE);
			map.put("flag", 1);
			List<TPermission> list = service.getPermissions(map);
			responseData.setData(list);
			responseData.setCode(Constants.SUCCESS);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			responseData.setCode(Constants.INNER_ERROR);
			responseData.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return responseData;
	}

	/**
	 * permissions展示页
	 * 
	 * @param permission
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData queryPermissionMenu(@RequestBody TPermission permission) {
		System.out.println(JSONObject.toJSONString(permission));
		ResponseData responseData = new ResponseData();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pDescription", permission.getpDescription());
		try {
			List<TPermission> list = perService.query(map);
			responseData.setData(list);
			responseData.setCode(Constants.SUCCESS);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			responseData.setCode(Constants.INNER_ERROR);
			responseData.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return responseData;
	}

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData find(@RequestBody TPermission permission) {
		System.out.println(JSONObject.toJSONString(permission));
		ResponseData responseData = new ResponseData();
		try {
			TPermission per = perService.find(permission.getpId());
			responseData.setData(per);
			responseData.setCode(Constants.SUCCESS);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			responseData.setCode(Constants.INNER_ERROR);
			responseData.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return responseData;
	}

	/**
	 * permission新增或修改接口
	 * 
	 * @param permission
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData savePermissionMenu(@RequestBody TPermission permission) {
		ResponseData responseData = new ResponseData();
		try {
			System.out.println(JSONObject.toJSONString(permission));
			permission.setpAddTime(new Date());
			if (permission.getpId() != null) {
				perService.update(permission);
				responseData.setMsg(Constants
						.getSuccessMsg(Constants.UPDATE_SUCCESS));
			} else {
				perService.insert(permission);
				responseData.setMsg(Constants
						.getSuccessMsg(Constants.INSERT_SUCCESS));
			}
			responseData.setCode(Constants.SUCCESS);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			responseData.setCode(Constants.FAILED);
			responseData.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return responseData;
	}

	/**
	 * permission删除接口
	 * 
	 * @param permission
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData deletePermissionMenu(@RequestBody TPermission permission) {
		ResponseData responseData = new ResponseData();
		try {
			perService.delete(permission.getpId());
			responseData.setCode(Constants.SUCCESS);
			responseData.setMsg(Constants
					.getSuccessMsg(Constants.DELETE_SUCCESS));
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			responseData.setCode(Constants.FAILED);
			responseData.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return responseData;
	}

	@RequestMapping("/rolePermissionTree")
	@ResponseBody
	public ResponseData rolePermissionTree(@RequestBody TRole role) {
		ResponseData responseData = new ResponseData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (role != null) {
				map.put("roleId", role.getrId());
			}
			List<RolePermissionTree> list = perService.userPermissionsTree(map);
			responseData.setCode(Constants.SUCCESS);
			responseData.setData(list);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			responseData.setCode(Constants.INNER_ERROR);
			responseData.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return responseData;
	}

	@RequestMapping(value = "/saveRolePermission", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData saveRolePermission(@RequestBody TPermission permission) {
		ResponseData responseData = new ResponseData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String permissionIds = permission.getpDescription();
			int roleId = permission.getpId();
			if (!StringUtils.isEmpty(permissionIds)) {
				String[] a = permissionIds.split(",");
				map.put("permissionIds", a);
				map.put("flag", "1");
			} else {
				map.put("flag", "0");
			}
			map.put("roleId", roleId);
			rpService.saveRolePermissions(map);
			responseData.setCode(Constants.SUCCESS);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			responseData.setCode(Constants.INNER_ERROR);
			responseData.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return responseData;
	}

	@RequestMapping(value = "/permissionSetList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseData permissionSetList() {
		ResponseData responseData = new ResponseData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<TPermission> list = perService.permissionSetList(map);
			responseData.setData(list);
			responseData.setCode(Constants.SUCCESS);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			responseData.setCode(Constants.INNER_ERROR);
			responseData.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return responseData;
	}

}
