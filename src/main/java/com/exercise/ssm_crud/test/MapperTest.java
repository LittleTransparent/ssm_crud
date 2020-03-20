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
 * 测试dao层工作
 * @author 为所欲为
 * 推荐spring单元测试可以自动注入需要的组件
 */

//指定使用哪个单元测试
@RunWith(SpringJUnit4ClassRunner.class)
//指定spring配置文件的位置
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
		
		//1.插入几个部门
		departmentMapper.insertSelective(new Department(null,"开发部"));
		departmentMapper.insertSelective(new Department(null,"测试部"));
		System.out.println("插入完成");
	}
	
	@Test
	public void EmployeeTest() {
		//2.简单的生成员工
		//employeeMapper.insertSelective(new Employee(null, "张三", "M", "zhangsan@163.com", 1));
		//3.批量插入，执行批量操作的sqlSession
		EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<1000;i++) {
			String uid=UUID.randomUUID().toString().substring(0,5)+i;
			mapper.insertSelective(new Employee(null, uid, "M", uid+"@163.com", 1));
		}
		System.out.println("批量完成");
	}
}
