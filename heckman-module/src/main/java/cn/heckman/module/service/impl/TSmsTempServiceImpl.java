package cn.heckman.module.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.heckman.module.dao.TSmsTempMapper;
import cn.heckman.module.pojo.TSmsTemp;
import cn.heckman.module.service.TSmsTempService;

@Service
public class TSmsTempServiceImpl implements TSmsTempService {

	@Autowired
	private TSmsTempMapper dao;

	public int insert(TSmsTemp t) {
		return dao.insert(t);
	}

	public void update(TSmsTemp t) {
		dao.update(t);
	}

	public List<TSmsTemp> query(Map<String, Object> map) {
		return dao.query(map);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public TSmsTemp find(Integer id) {
		return dao.find(id);
	}

	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

}
