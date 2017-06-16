package cn.heckman.module.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.heckman.framework.utils.ShiroSessionUtil;
import cn.heckman.module.common.OperateLogConstants;
import cn.heckman.module.dao.TOperateLogMapper;
import cn.heckman.module.dao.TRoleMapper;
import cn.heckman.module.pojo.TOperateLog;
import cn.heckman.module.pojo.TRole;
import cn.heckman.module.pojo.TUser;
import cn.heckman.module.service.TRoleService;

@Service
public class TRoleServiceImpl implements TRoleService {

	@Autowired
	private TRoleMapper dao;

	@Autowired
	private TOperateLogMapper logDao;

	public int insert(TRole t) {
		/*logDao.insert(new TOperateLog(
				OperateLogConstants.ROLE_INSERT_OPTION,
				OperateLogConstants
						.getOperateLogDes(OperateLogConstants.ROLE_INSERT_OPTION)
						+ ":role_code=" + t.getrName(),
				((TUser) ShiroSessionUtil
						.getSession(ShiroSessionUtil.USER_SESSION_NAME))
						.getuId(), ((TUser) ShiroSessionUtil
						.getSession(ShiroSessionUtil.USER_SESSION_NAME))
						.getuUsername()));*/
		return dao.insert(t);
	}

	public void update(TRole t) {
		/*logDao.insert(new TOperateLog(
				OperateLogConstants.ROLE_UPDATE_OPTION,
				OperateLogConstants
						.getOperateLogDes(OperateLogConstants.ROLE_UPDATE_OPTION)
						+ ":role_code=" + t.getrName(),
				((TUser) ShiroSessionUtil
						.getSession(ShiroSessionUtil.USER_SESSION_NAME))
						.getuId(), ((TUser) ShiroSessionUtil
						.getSession(ShiroSessionUtil.USER_SESSION_NAME))
						.getuUsername()));*/
		dao.update(t);
	}

	public List<TRole> query(Map<String, Object> map) {
		return dao.query(map);
	}

	public void delete(Integer id) {
		/*logDao.insert(new TOperateLog(
				OperateLogConstants.ROLE_DELETE_OPTION,
				OperateLogConstants
						.getOperateLogDes(OperateLogConstants.ROLE_DELETE_OPTION)
						+ ":id=" + id, ((TUser) ShiroSessionUtil
						.getSession(ShiroSessionUtil.USER_SESSION_NAME))
						.getuId(), ((TUser) ShiroSessionUtil
						.getSession(ShiroSessionUtil.USER_SESSION_NAME))
						.getuUsername()));*/
		dao.delete(id);
	}

	public TRole find(Integer id) {
		return dao.find(id);
	}

	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

}
