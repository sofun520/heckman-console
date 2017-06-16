package cn.heckman.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.heckman.framework.utils.Constants;
import cn.heckman.framework.utils.MD5Util;
import cn.heckman.framework.utils.ResponseData;
import cn.heckman.module.pojo.TUser;
import cn.heckman.module.service.TUserService;

@Controller
public class CheckLogin {

	@Autowired
	private TUserService userService;

	private Logger logger = Logger.getLogger(CheckLogin.class);

	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseData login2(@RequestBody TUser user) {
		ResponseData responseData = new ResponseData();
		UsernamePasswordToken token = new UsernamePasswordToken(
				user.getuUsername(), MD5Util.md5(user.getuPassword()));
		token.setRememberMe(true);
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
		} catch (UnknownAccountException uae) {
			responseData.setCode(Constants.UNKOWN_ACCOUNT);
			responseData.setMsg(Constants.getErrMsg(Constants.UNKOWN_ACCOUNT));
		} catch (IncorrectCredentialsException ice) {
			responseData.setCode(Constants.INCORRECT_CREDENTIALS);
			responseData.setMsg(Constants
					.getErrMsg(Constants.INCORRECT_CREDENTIALS));
		} catch (LockedAccountException lae) {
			responseData.setCode(Constants.LOCKED_ACCOUNT);
			responseData.setMsg(Constants.getErrMsg(Constants.LOCKED_ACCOUNT));
		} catch (ExcessiveAttemptsException eae) {
			responseData.setCode(Constants.EXCESSIVE_ATTEMPS);
			responseData.setMsg(Constants
					.getErrMsg(Constants.EXCESSIVE_ATTEMPS));
		} catch (AuthenticationException ae) {
			responseData.setCode(Constants.AUTHENTICATION);
			responseData.setMsg(Constants.getErrMsg(Constants.AUTHENTICATION));
		}

		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uUsername", user.getuUsername());
			responseData.setData(userService.findUser(user));
			responseData.setCode(Constants.SUCCESS);
			responseData.setMsg("登录成功");
		} else {
			token.clear();
		}

		return responseData;
	}

	/**
	 * 
	 * 用户登出
	 */

	@RequestMapping("/logout")
	@ResponseBody
	public ResponseData logout(HttpServletRequest request) {
		ResponseData rd = new ResponseData();
		try {
			SecurityUtils.getSubject().logout();
			rd.setCode(Constants.SUCCESS);
			rd.setMsg(Constants.getSuccessMsg(Constants.LOGOUT_SUCCESS));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rd;
	}
}
