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
 * ʹ��Spring����ģ����������ܣ�����crud׼ȷ��
 * @author Ϊ����Ϊ
 *
 */


//ָ��ʹ���ĸ���Ԫ����
@RunWith(SpringJUnit4ClassRunner.class)
//ָ��spring�����ļ���λ��
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc.xml"})
//@autowiredֻ���Զ�װ��ioc�ڲ����������ע������Զ�����
@WebAppConfiguration
public class PageTest {
	//����springMvc��ioc
	@Autowired
	WebApplicationContext context;
	
	//����MVC
	MockMvc mockMvc;
	
	//ע���õ���junit��@Before
	@Before
	//����MVC��Ҫ��ʼ��
	public void initMockMvc() {
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void test() throws Exception {
		//ģ�ⷢ������get/post/put/delete���У�����õ�����ֵ
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "5")).andReturn();
		//����ɹ��Ժ�����������pageinfo������ȡ��pageinfo��֤
		MockHttpServletRequest request=mvcResult.getRequest();
		PageInfo info=(PageInfo) request.getAttribute("pageInfo");
		
		System.out.println("��ǰҳ�룺"+info.getPageNum());
		System.out.println("��ҳ�룺"+info.getPages());
		System.out.println("�ܼ�¼����"+info.getTotal());
		int[] nums=info.getNavigatepageNums();
		System.out.println("��ҳ��������ʾ��ҳ�룺");
		for(int i:nums) {
			System.out.print(i+" ");
		}
		System.out.print("\n");
		//Ա������
		List<Employee> list=info.getList();
		for(Employee e:list) {
			System.out.println("ID��"+e.getEmpId()+"��������"+e.getEmpName());
		}
	}
}
