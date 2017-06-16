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
import cn.heckman.module.pojo.TSmsTemp;
import cn.heckman.module.pojo.TUser;
import cn.heckman.module.service.TSmsTempService;

@Controller
@RequestMapping("/tSmsTemp")
public class TSmsTempController {

	@Autowired
	private TSmsTempService service;

	@RequestMapping("/query")
	@ResponseBody
	public ResponseData query(@RequestBody Map<String, Object> model) {
		ResponseData rd = new ResponseData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			TSmsTemp smsTemp = JsonXMLUtils.map2obj(
					(Map<String, Object>) model.get("param"), TSmsTemp.class);

			PageBean pageBean = JsonXMLUtils
					.map2obj((Map<String, Object>) model.get("pageBean"),
							PageBean.class);

			if (pageBean.getPage() != null && pageBean.getPageSize() != null) {
				if (smsTemp == null) {
					smsTemp = new TSmsTemp();
				}
				map.put("pageBean", pageBean);
				map.put("smsTemp", smsTemp);
				int total = service.count(map);
				int startIndex = (pageBean.getPage() - 1)
						* pageBean.getPageSize();
				pageBean.setStartIndex(startIndex);
				List<TSmsTemp> list = service.query(map);
				rd.setData(list);
				rd.setPage(pageBean.getPage());
				rd.setPageCount((total + pageBean.getPageSize() - 1)
						/ pageBean.getPageSize());
				rd.setPageSize(pageBean.getPageSize());
				rd.setTotal(total);
				rd.setCode(Constants.SUCCESS);
			} else {
				rd.setCode(Constants.FAILED);
				rd.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			rd.setCode(Constants.FAILED);
			rd.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return rd;
	}

	@RequestMapping("/save")
	@ResponseBody
	public ResponseData save(@RequestBody Map<String, Object> model) {
		ResponseData rd = new ResponseData();
		try {
			TSmsTemp temp = JsonXMLUtils.map2obj(
					(Map<String, Object>) model.get("smsTemp"), TSmsTemp.class);

			TUser user = JsonXMLUtils.map2obj(
					(Map<String, Object>) model.get("user"), TUser.class);

			if (temp.gettId() != null) {
				temp.settUpdateUser(user.getuId());
				temp.settUpdateTime(new Date());
				service.update(temp);
				rd.setMsg(Constants.getSuccessMsg(Constants.UPDATE_SUCCESS));
			} else {
				temp.settAddUser(user.getuId());
				temp.settAddTime(new Date());
				service.insert(temp);
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
	public ResponseData find(@RequestBody TSmsTemp temp) {
		ResponseData rd = new ResponseData();
		try {
			if (temp != null && temp.gettId() != null) {
				int id = temp.gettId();
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
	public ResponseData delete(@RequestBody TSmsTemp temp) {
		ResponseData rd = new ResponseData();
		try {
			if (temp.gettId() != null) {
				service.delete(temp.gettId());
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

}
