package com.exercise.ssm_crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.ssm_crud.bean.Department;
import com.exercise.ssm_crud.dao.DepartmentMapper;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentMapper departmentMapper;

	//查出所有部门
	public List<Department> getDepts() {
		//按条件查询，条件为null，即所有都查
		List<Department> list=departmentMapper.selectByExample(null);
		return list;
	}
}
