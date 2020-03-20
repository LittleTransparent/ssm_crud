package com.exercise.ssm_crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.ssm_crud.bean.Employee;
import com.exercise.ssm_crud.bean.EmployeeExample;
import com.exercise.ssm_crud.bean.EmployeeExample.Criteria;
import com.exercise.ssm_crud.dao.EmployeeMapper;

//ע����ҵ���߼����
@Service
public class EmployeeService {
	//����dao��
	@Autowired
	EmployeeMapper employeeMapper;
	
	//��ѯ����Ա��
	public List<Employee> getAll() {
		//�����У�����Ҫָ��������������null
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	//����Ա��
	public void saveEmp(Employee employee) {
		//id���������ԣ�����ʱû��Ҫ����id����������ѡ��Ĳ���ķ���
		employeeMapper.insertSelective(employee);
	}
	
	//�����û����Ƿ��ظ�
	public boolean checkUser(String empName) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria=example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count=employeeMapper.countByExample(example);
		//==0�����ظ�
		return count==0;
	}
	
	//��Ա��id��ѯԱ��
	public Employee getEmp(Integer id) {
		Employee employee=employeeMapper.selectByPrimaryKey(id);
		return employee;
	}
	
	//Ա������
	public void updateEmp(Employee employee) {
		//����ʱ������Ա��������������ѡ��ĸ���
		//ͨ��"/emp/{id}"���£������ǰ���������
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
	
	//ɾ��
	public void deleteEmp(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
	}
	
	//����ɾ��
	public void deleteBatch(List<Integer> ids) {
		EmployeeExample example=new EmployeeExample();
		Criteria criteria=example.createCriteria();
		//delete from xxx where emp_id in(1,2,3,...)
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
	}
}
