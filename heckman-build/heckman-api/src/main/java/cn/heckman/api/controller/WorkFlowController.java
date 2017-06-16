package cn.heckman.api.controller;

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
import cn.heckman.framework.utils.JsonXMLUtils;
import cn.heckman.framework.utils.ResponseData;
import cn.heckman.module.common.PageBean;
import cn.heckman.module.pojo.TWorkFlow;
import cn.heckman.module.service.TWorkFlowService;

@Controller
@RequestMapping("/workFlow")
public class WorkFlowController {

	@Autowired
	private TWorkFlowService service;

	private Logger logger = Logger.getLogger(WorkFlowController.class);

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData queryPermissionMenu(
			@RequestBody Map<String, Object> model) {
		ResponseData responseData = new ResponseData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			TWorkFlow workFlow = JsonXMLUtils.map2obj(
					(Map<String, Object>) model.get("param"), TWorkFlow.class);
			PageBean pageBean = JsonXMLUtils
					.map2obj((Map<String, Object>) model.get("pageBean"),
							PageBean.class);
			if (workFlow == null) {
				workFlow = new TWorkFlow();
			}
			if (pageBean != null) {
				pageBean.setStartIndex((pageBean.getPage() - 1)
						* pageBean.getPageSize());
				map.put("pageBean", pageBean);
			}
			map.put("workFlow", workFlow);
			List<TWorkFlow> list = service.query(map);
			int total = service.count(map);
			responseData.setData(list);
			responseData.setCode(Constants.SUCCESS);
			if (pageBean != null) {
				responseData.setPage(pageBean.getPage());
				responseData.setPageCount((pageBean.getPageSize() + total - 1)
						/ pageBean.getPageSize());
				responseData.setPageSize(pageBean.getPageSize());
				responseData.setTotal(total);
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.error(ex);
			responseData.setCode(Constants.INNER_ERROR);
			responseData.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return responseData;
	}

}
