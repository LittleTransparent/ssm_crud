package com.exercise.ssm_crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.exercise.ssm_crud.bean.Employee;
import com.github.pagehelper.PageInfo;

/**
 * 使用Spring测试模块测试请求功能，测试crud准确性
 * @author 为所欲为
 *
 */


//指定使用哪个单元测试
@RunWith(SpringJUnit4ClassRunner.class)
//指定spring配置文件的位置
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc.xml"})
//@autowired只能自动装配ioc内部，加上这个注解才能自动传入
@WebAppConfiguration
public class PageTest {
	//传入springMvc的ioc
	@Autowired
	WebApplicationContext context;
	
	//虚拟MVC
	MockMvc mockMvc;
	
	//注意用的是junit的@Before
	@Before
	//虚拟MVC需要初始化
	public void initMockMvc() {
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void test() throws Exception {
		//模拟发送请求，get/post/put/delete都行，最后拿到返回值
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "5")).andReturn();
		//请求成功以后，请求域中有pageinfo，可以取出pageinfo验证
		MockHttpServletRequest request=mvcResult.getRequest();
		PageInfo info=(PageInfo) request.getAttribute("pageInfo");
		
		System.out.println("当前页码："+info.getPageNum());
		System.out.println("总页码："+info.getPages());
		System.out.println("总记录数："+info.getTotal());
		int[] nums=info.getNavigatepageNums();
		System.out.println("在页面连续显示的页码：");
		for(int i:nums) {
			System.out.print(i+" ");
		}
		System.out.print("\n");
		//员工数据
		List<Employee> list=info.getList();
		for(Employee e:list) {
			System.out.println("ID："+e.getEmpId()+"；姓名："+e.getEmpName());
		}
	}
}
