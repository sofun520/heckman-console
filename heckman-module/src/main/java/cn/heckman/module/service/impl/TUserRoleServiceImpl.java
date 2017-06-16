package cn.heckman.module.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.heckman.module.dao.TUserRoleMapper;
import cn.heckman.module.pojo.TUserRole;
import cn.heckman.module.service.TUserRoleService;

@Service
public class TUserRoleServiceImpl implements TUserRoleService {

	@Autowired
	private TUserRoleMapper dao;

	public int insert(TUserRole t) {
		return dao.insert(t);
	}

	public void update(TUserRole t) {
		dao.update(t);
	}

	public List<TUserRole> query(Map<String, Object> map) {
		return dao.query(map);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public TUserRole find(Integer id) {
		return dao.find(id);
	}

	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

}
