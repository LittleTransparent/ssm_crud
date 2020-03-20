package com.exercise.ssm_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exercise.ssm_crud.bean.Department;
import com.exercise.ssm_crud.bean.Message;
import com.exercise.ssm_crud.service.DepartmentService;

/**
 * ����Ͳ����йص�����
 * @author Ϊ����Ϊ
 *
 */
@Controller
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	
	//�������в�����Ϣ
	@RequestMapping("/depts")
	@ResponseBody
	public Message getDepts() {
		List<Department> list=departmentService.getDepts();
		return Message.success().add("depts",list);
	}
}
