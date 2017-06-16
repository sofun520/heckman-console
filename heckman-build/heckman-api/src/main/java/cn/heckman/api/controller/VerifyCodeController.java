package cn.heckman.api.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.heckman.framework.utils.Constants;
import cn.heckman.framework.utils.ResponseData;
import cn.heckman.framework.utils.ShiroSessionUtil;
import cn.heckman.framework.utils.VerifyCodeUtil;

import com.alibaba.druid.util.StringUtils;

/**
 * 验证码
 * 
 * @author heckman
 *
 */
@Controller
@RequestMapping("/verifyCode")
public class VerifyCodeController {

	private Logger logger = Logger.getLogger(VerifyCodeController.class);

	@RequestMapping("/getVerifyCodeImage")
	public void getVerifyCodeImage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 设置页面不缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String verifyCode = VerifyCodeUtil.generateTextCode(
				VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
		// 将验证码放到HttpSession里面
		// request.getSession().setAttribute("verifyCode", verifyCode);

		ShiroSessionUtil.setSession("verifyCode", verifyCode);

		System.out.println("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
		// 设置输出的内容的类型为JPEG图像
		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(
				verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
		// 写给浏览器
		ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
	}

	@RequestMapping(value = "/checkVerifyCode", method = RequestMethod.GET)
	@ResponseBody
	public ResponseData checkVerifyCode(HttpServletRequest request) {
		ResponseData responseData = new ResponseData();
		try {
			String submitCode = request.getParameter("verifyCode");
			/*
			 * String verifyCode = (String) request.getSession().getAttribute(
			 * "verifyCode");
			 */
			String verifyCode = (String) ShiroSessionUtil
					.getSession("verifyCode");
			logger.debug("submitCode===" + submitCode);
			logger.debug("verifyCode====" + verifyCode);
			if (StringUtils.isEmpty(submitCode)
					|| !StringUtils
							.equals(verifyCode, submitCode.toLowerCase())) {
				responseData.setCode(Constants.INCORRECT_VERIFYCODE);
				responseData.setMsg(Constants
						.getErrMsg(Constants.INCORRECT_VERIFYCODE));
			} else {
				responseData.setCode(Constants.SUCCESS);
				responseData.setMsg("验证码正确");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			// logger.error(ex);
		}
		return responseData;
	}

}
