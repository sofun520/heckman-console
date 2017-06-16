package cn.heckman.module.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.heckman.module.dao.TRolePermissionMapper;
import cn.heckman.module.pojo.TRolePermission;
import cn.heckman.module.service.TRolePermissionService;

@Service
public class TRolePermissionServiceImpl implements TRolePermissionService {

	@Autowired
	private TRolePermissionMapper dao;

	public int insert(TRolePermission t) {
		return dao.insert(t);
	}

	public void update(TRolePermission t) {
		dao.update(t);
	}

	public List<TRolePermission> query(Map<String, Object> map) {
		return dao.query(map);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public TRolePermission find(Integer id) {
		return dao.find(id);
	}

	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

	public void saveRolePermissions(Map<String, Object> map) {
		dao.deleteRolePermissions(map);
		if ("1".equals(map.get("flag"))) {
			dao.saveRolePermissions(map);
		}
	}

}
