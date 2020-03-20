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
 * 处理和部门有关的请求
 * @author 为所欲为
 *
 */
@Controller
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	
	//返回所有部门信息
	@RequestMapping("/depts")
	@ResponseBody
	public Message getDepts() {
		List<Department> list=departmentService.getDepts();
		return Message.success().add("depts",list);
	}
}
