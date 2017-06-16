package cn.heckman.module.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.heckman.module.dao.TWorkFlowDao;
import cn.heckman.module.pojo.TWorkFlow;
import cn.heckman.module.service.TWorkFlowService;

@Service
public class TWorkFlowServiceImpl implements TWorkFlowService {

	@Autowired
	private TWorkFlowDao dao;

	public int insert(TWorkFlow t) {
		return dao.insert(t);
	}

	public void update(TWorkFlow t) {
		dao.update(t);
	}

	public List<TWorkFlow> query(Map<String, Object> map) {
		return dao.query(map);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public TWorkFlow find(Integer id) {
		return dao.find(id);
	}

	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

}
