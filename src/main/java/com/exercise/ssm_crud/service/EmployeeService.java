package com.exercise.ssm_crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.ssm_crud.bean.Employee;
import com.exercise.ssm_crud.bean.EmployeeExample;
import com.exercise.ssm_crud.bean.EmployeeExample.Criteria;
import com.exercise.ssm_crud.dao.EmployeeMapper;

//注明是业务逻辑组件
@Service
public class EmployeeService {
	//调用dao层
	@Autowired
	EmployeeMapper employeeMapper;
	
	//查询所有员工
	public List<Employee> getAll() {
		//查所有，不需要指定条件，所以是null
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	//保存员工
	public void saveEmp(Employee employee) {
		//id是自增属性，插入时没必要带着id，所以用有选择的插入的方法
		employeeMapper.insertSelective(employee);
	}
	
	//检验用户名是否重复
	public boolean checkUser(String empName) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria=example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count=employeeMapper.countByExample(example);
		//==0则无重复
		return count==0;
	}
	
	//按员工id查询员工
	public Employee getEmp(Integer id) {
		Employee employee=employeeMapper.selectByPrimaryKey(id);
		return employee;
	}
	
	//员工更新
	public void updateEmp(Employee employee) {
		//更新时不更新员工名，所以用有选择的更新
		//通过"/emp/{id}"更新，所以是按主键更新
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
	
	//删除
	public void deleteEmp(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
	}
	
	//批量删除
	public void deleteBatch(List<Integer> ids) {
		EmployeeExample example=new EmployeeExample();
		Criteria criteria=example.createCriteria();
		//delete from xxx where emp_id in(1,2,3,...)
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
	}
}
