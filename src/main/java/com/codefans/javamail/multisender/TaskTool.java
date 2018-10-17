package com.codefans.javamail.multisender;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.codefans.spring.db.DBUtil;

public class TaskTool {

	JdbcTemplate jdbcTemplate = null;

	public TaskTool() {
		jdbcTemplate = DBUtil.getInstance().getJdbcTemplate();
	}

	public List<Map<String, Object>> getAllUsers() {
		List<Map<String, Object>> list = null;
		String sql = "select * from mailuser";
		list = (List<Map<String, Object>>) queryForList(sql);
		return list;
	}

	public Object queryForList(String sql) {
		return jdbcTemplate.queryForList(sql);
	}
}
