package com.exercise.ssm_crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exercise.ssm_crud.bean.Department;
import com.exercise.ssm_crud.bean.Employee;
import com.exercise.ssm_crud.dao.DepartmentMapper;
import com.exercise.ssm_crud.dao.EmployeeMapper;


/**
 * ����dao�㹤��
 * @author Ϊ����Ϊ
 * �Ƽ�spring��Ԫ���Կ����Զ�ע����Ҫ�����
 */

//ָ��ʹ���ĸ���Ԫ����
@RunWith(SpringJUnit4ClassRunner.class)
//ָ��spring�����ļ���λ��
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void departmentTest() {
		/*
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml"); 
		DepartmentMapper bean=context.getBean(DepartmentMapper.class);
		*/
		
		System.out.println(departmentMapper);
		
		//1.���뼸������
		departmentMapper.insertSelective(new Department(null,"������"));
		departmentMapper.insertSelective(new Department(null,"���Բ�"));
		System.out.println("�������");
	}
	
	@Test
	public void EmployeeTest() {
		//2.�򵥵�����Ա��
		//employeeMapper.insertSelective(new Employee(null, "����", "M", "zhangsan@163.com", 1));
		//3.�������룬ִ������������sqlSession
		EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<1000;i++) {
			String uid=UUID.randomUUID().toString().substring(0,5)+i;
			mapper.insertSelective(new Employee(null, uid, "M", uid+"@163.com", 1));
		}
		System.out.println("�������");
	}
}
