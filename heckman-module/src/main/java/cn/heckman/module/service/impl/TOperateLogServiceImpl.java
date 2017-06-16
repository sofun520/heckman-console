package cn.heckman.module.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.heckman.module.dao.TOperateLogMapper;
import cn.heckman.module.pojo.TOperateLog;
import cn.heckman.module.service.TOperateLogService;

@Service
public class TOperateLogServiceImpl implements TOperateLogService {

	@Autowired
	private TOperateLogMapper dao;

	public int insert(TOperateLog t) {
		return dao.insert(t);
	}

	public void update(TOperateLog t) {
		dao.update(t);
	}

	public List<TOperateLog> query(Map<String, Object> map) {
		return dao.query(map);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public TOperateLog find(Integer id) {
		return dao.find(id);
	}

	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

}
