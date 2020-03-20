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

	//������в���
	public List<Department> getDepts() {
		//��������ѯ������Ϊnull�������ж���
		List<Department> list=departmentMapper.selectByExample(null);
		return list;
	}
}
