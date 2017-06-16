package cn.heckman.api.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import cn.heckman.module.pojo.TOperateLog;
import cn.heckman.module.service.TOperateLogService;

@Controller
@RequestMapping("/operateLog")
public class OperateLogController {

	@Autowired
	private TOperateLogService service;

	private Logger logger = Logger.getLogger(OperateLogController.class);

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData queryPermissionMenu(
			@RequestBody Map<String, Object> model) {
		ResponseData responseData = new ResponseData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			TOperateLog operateLog = JsonXMLUtils
					.map2obj((Map<String, Object>) model.get("param"),
							TOperateLog.class);
			PageBean pageBean = JsonXMLUtils
					.map2obj((Map<String, Object>) model.get("pageBean"),
							PageBean.class);
			if (operateLog == null) {
				operateLog = new TOperateLog();
			}
			pageBean.setStartIndex((pageBean.getPage() - 1)
					* pageBean.getPageSize());
			map.put("pageBean", pageBean);
			map.put("operateLog", operateLog);
			List<TOperateLog> list = service.query(map);
			int total = service.count(map);
			responseData.setData(list);
			responseData.setCode(Constants.SUCCESS);
			responseData.setPage(pageBean.getPage());
			responseData.setPageCount((pageBean.getPageSize() + total - 1)
					/ pageBean.getPageSize());
			responseData.setPageSize(pageBean.getPageSize());
			responseData.setTotal(total);
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.error(ex);
			responseData.setCode(Constants.INNER_ERROR);
			responseData.setMsg(Constants.getErrMsg(Constants.INNER_ERROR));
		}
		return responseData;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
	@ResponseBody
	public void exportExcel(@RequestBody Map<String, Object> model,
			HttpServletResponse response) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("操作日志");
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中格式
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		/*
		 * String[] titles = new String[]{"序号","业务代码","操作描述","操作人","操作时间"};
		 * HSSFCell cell; for(int i=0;i<titles.length;i++){ cell =
		 * sheet.createRow(0).createCell(i); cell.setCellValue(titles[i]);
		 * cell.setCellStyle(style); }
		 */
		HSSFRow row1 = sheet.createRow(0);
		HSSFCell cell0 = row1.createCell(0);
		cell0.setCellStyle(style);
		cell0.setCellValue("序号");
		HSSFCell cell1 = row1.createCell(1);
		cell1.setCellStyle(style);
		cell1.setCellValue("业务代码");
		HSSFCell cell2 = row1.createCell(2);
		cell2.setCellStyle(style);
		cell2.setCellValue("操作描述");
		HSSFCell cell3 = row1.createCell(3);
		cell3.setCellStyle(style);
		cell3.setCellValue("操作人");
		HSSFCell cell4 = row1.createCell(4);
		cell4.setCellStyle(style);
		cell4.setCellValue("操作时间");
		sheet.setColumnWidth(2, 100 * 256);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			TOperateLog operateLog = JsonXMLUtils
					.map2obj((Map<String, Object>) model.get("param"),
							TOperateLog.class);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("operateLog", operateLog);

			List<TOperateLog> list = service.query(map);
			if (list.size() > 0) {
				HSSFRow row;
				TOperateLog log;
				for (int i = 0; i < list.size(); i++) {
					log = list.get(i);
					row = sheet.createRow(i + 1);
					row.createCell(0).setCellValue(i + 1);
					row.createCell(1).setCellValue(log.getoBusCode());
					row.createCell(2).setCellValue(log.getoBusDescription());
					row.createCell(3).setCellValue(log.getoUserName());
					row.createCell(4).setCellValue(
							formatter.format(log.getoAddTime()));
				}
			}
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName="
					+ new String("操作日志表".getBytes("utf-8"), "iso-8859-1"));

			OutputStream out = response.getOutputStream();
			wb.write(out);
			out.flush();
			out.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			// logger.error(ex);
		}
	}
}
