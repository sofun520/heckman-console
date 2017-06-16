package cn.heckman.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.heckman.framework.utils.Constants;
import cn.heckman.framework.utils.JsonXMLUtils;
import cn.heckman.framework.utils.ResponseData;
import cn.heckman.module.common.PageBean;
import cn.heckman.module.pojo.TOperateLog;
import cn.heckman.module.pojo.TRole;
import cn.heckman.module.pojo.TRolePermission;
import cn.heckman.module.service.TRolePermissionService;
import cn.heckman.module.service.TRoleService;

@Controller
@RequestMapping("/role")
public class TRoleController {

	@Autowired
	private TRoleService service;

	@Autowired
	private TRolePermissionService rpService;

	@RequestMapping("/query")
	@ResponseBody
	public ResponseData query(@RequestBody Map<String, Object> model) {
		ResponseData rd = new ResponseData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			TRole role = JsonXMLUtils.map2obj(
					(Map<String, Object>) model.get("param"), TRole.class);
			PageBean pageBean = JsonXMLUtils
					.map2obj((Map<String, Object>) model.get("pageBean"),
							PageBean.class);
			int total = 0;
			int pageCount = 0;
			if (role == null) {
				role = new TRole();
			}
			if (pageBean == null) {
				pageBean = new PageBean();
			} else if (pageBean != null && pageBean.getPage() != null
					&& pageBean.getPageSize() != null) {
				pageBean.setStartIndex((pageBean.getPage() - 1)
						* pageBean.getPageSize());
				map.put("pageBean", pageBean);
				rd.setPage(pageBean.getPage());
				rd.setPageSize(pageBean.getPageSize());
				pageCount = (total + pageBean.getPageSize() - 1)
						/ pageBean.getPageSize();
			}
			map.put("role", role);
			total = service.count(map);
			List<TRole> list = service.query(map);

			rd.setPageCount(pageCount);
			rd.setTotal(total);
			rd.setData(list);
			rd.setCode(Constants.SUCCESS);
		} catch (Exception ex) {
			ex.printStackTrace();
			rd.setCode(Constants.FAILED);
			rd.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return rd;
	}

	@RequestMapping("/save")
	@ResponseBody
	public ResponseData save(@RequestBody TRole role) {
		ResponseData rd = new ResponseData();
		try {
			role.setrAddTime(new Date());
			if (role.getrId() != null) {
				service.update(role);
				rd.setMsg(Constants.getSuccessMsg(Constants.UPDATE_SUCCESS));
			} else {
				service.insert(role);
				rd.setMsg(Constants.getSuccessMsg(Constants.INSERT_SUCCESS));
			}
			rd.setCode(Constants.SUCCESS);
		} catch (Exception ex) {
			ex.printStackTrace();
			rd.setCode(Constants.FAILED);
			rd.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return rd;
	}

	@RequestMapping("/find")
	@ResponseBody
	public ResponseData find(@RequestBody TRole role) {
		ResponseData rd = new ResponseData();
		try {
			if (role != null && role.getrId() != null) {
				int id = role.getrId();
				rd.setData(service.find(id));
				rd.setCode(Constants.SUCCESS);
				rd.setMsg(Constants.getSuccessMsg(Constants.QUERY_SUCCESS));
			} else {
				rd.setCode(Constants.SUCCESS);
				rd.setMsg(Constants.getSuccessMsg(Constants.QUERY_NULL));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			rd.setCode(Constants.FAILED);
			rd.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return rd;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResponseData delete(@RequestBody TRole role) {
		ResponseData rd = new ResponseData();
		System.out.println(role == null);
		System.out.println(role.getrId());
		try {
			if (role.getrId() != null) {
				service.delete(role.getrId());
				rd.setMsg(Constants.getSuccessMsg(Constants.DELETE_SUCCESS));
			}
			rd.setCode(Constants.SUCCESS);
		} catch (Exception ex) {
			ex.printStackTrace();
			rd.setCode(Constants.FAILED);
			rd.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return rd;
	}

	@RequestMapping("/saveRolePermission")
	@ResponseBody
	public ResponseData saveRolePermission(@RequestBody TRolePermission rolePer) {
		ResponseData rd = new ResponseData();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", rolePer.getRoleId());
			map.put("permissionId", rolePer.getPermissionId());
			List<TRolePermission> list = rpService.query(map);
			if (list.size() > 0) {
				// 存在则删除
				rpService.delete(list.get(0).getId());
			} else {
				// 不存在则新增
				rpService.insert(rolePer);
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
