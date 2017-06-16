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
import org.springframework.web.bind.annotation.ResponseBody;

import cn.heckman.framework.utils.Constants;
import cn.heckman.framework.utils.ResponseData;
import cn.heckman.framework.utils.ShiroSessionUtil;
import cn.heckman.module.pojo.TUser;
import cn.heckman.module.pojo.TUserRole;
import cn.heckman.module.pojo.UserRoleTree;
import cn.heckman.module.service.TUserRoleService;
import cn.heckman.module.service.TUserService;

import com.alibaba.druid.util.StringUtils;

@Controller
@RequestMapping("/user")
public class TUserController {

	@Autowired
	private TUserService service;

	@Autowired
	private TUserRoleService urService;

	private Logger logger = Logger.getLogger(TUserController.class);

	@RequestMapping("/query")
	@ResponseBody
	public ResponseData query(@RequestBody TUser user) {
		ResponseData rd = new ResponseData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (user != null) {
				map.put("uUsername", user.getuUsername());
				map.put("uPhone", user.getuPhone());
				map.put("uEmail", user.getuEmail());
			}
			List<TUser> list = service.queryPage(map);
			rd.setData(list);
			rd.setCode(Constants.SUCCESS);
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.error(ex);
			rd.setCode(Constants.FAILED);
			rd.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return rd;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResponseData delete(@RequestBody TUser user) {
		ResponseData rd = new ResponseData();
		try {
			if (user != null) {
				int id = user.getuId();
				service.delete(id);
				rd.setData(Constants.getSuccessMsg(Constants.INSERT_SUCCESS));
				rd.setCode(Constants.SUCCESS);
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.error(ex);
			rd.setCode(Constants.FAILED);
			rd.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return rd;
	}

	@RequestMapping("/save")
	@ResponseBody
	public ResponseData save(@RequestBody TUser user) {
		ResponseData rd = new ResponseData();
		try {
			if (user != null) {
				user.setuAddTime(new Date());
				if (user.getuId() != null) {
					service.update(user);
					rd.setMsg(Constants.getSuccessMsg(Constants.UPDATE_SUCCESS));
				} else {
					service.insert(user);
					rd.setMsg(Constants.getSuccessMsg(Constants.INSERT_SUCCESS));
				}
				rd.setCode(Constants.SUCCESS);
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.error(ex);
			rd.setCode(Constants.FAILED);
			rd.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return rd;
	}

	@RequestMapping("/find")
	@ResponseBody
	public ResponseData find(@RequestBody TUser user) {
		ResponseData rd = new ResponseData();
		try {
			if (user != null && user.getuId() != null) {
				int id = user.getuId();
				TUser user1 = service.find(id);
				rd.setData(user1);
				rd.setCode(Constants.SUCCESS);
			} else {
				rd.setCode(Constants.QUERY_NULL);
				rd.setMsg(Constants.getSuccessMsg(Constants.QUERY_NULL));
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.error(ex);
			rd.setCode(Constants.FAILED);
			rd.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return rd;
	}

	@RequestMapping("/userInfo")
	@ResponseBody
	public ResponseData userInfo() {
		ResponseData rd = new ResponseData();
		try {
			TUser user = (TUser) ShiroSessionUtil.getSession("userinfo");
			rd.setData(user);
			rd.setCode(Constants.SUCCESS);
		} catch (Exception ex) {
			ex.printStackTrace();
			rd.setCode(Constants.FAILED);
			rd.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return rd;
	}

	@RequestMapping("/userRoleTree")
	@ResponseBody
	public ResponseData userRoleTree(@RequestBody TUser user) {
		ResponseData rd = new ResponseData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (user != null) {
				map.put("userId", user.getuId());
			}
			List<UserRoleTree> list = service.userRoleTree(map);
			rd.setData(list);
			rd.setCode(Constants.SUCCESS);
		} catch (Exception ex) {
			ex.printStackTrace();
			rd.setCode(Constants.FAILED);
			rd.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return rd;
	}

	@RequestMapping("/saveUserRole")
	@ResponseBody
	public ResponseData saveUserRole(@RequestBody TUserRole userRole) {
		ResponseData rd = new ResponseData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (userRole != null) {
				map.put("userId", userRole.getUserId());
				map.put("roleId", userRole.getRoleId());
				List<TUserRole> list = urService.query(map);
				if (list.size() > 0) {
					// 存在
					urService.delete(list.get(0).getId());
				} else {
					// 不存在
					urService.insert(userRole);
				}
			}
			rd.setCode(Constants.SUCCESS);
		} catch (Exception ex) {
			ex.printStackTrace();
			rd.setCode(Constants.FAILED);
			rd.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return rd;
	}

}
