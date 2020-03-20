package com.exercise.ssm_crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exercise.ssm_crud.bean.Employee;
import com.exercise.ssm_crud.bean.Message;
import com.exercise.ssm_crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * 	����Ա��crud����
 * @author Ϊ����Ϊ
 *
 */
@Controller
public class EmployeeController {
	//��Ҫ����service���ҵ���߼����
	@Autowired
	EmployeeService employeeService;

	
	//�����Զ����emps����
	@RequestMapping("/emps2")
	public String getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model) {
		//ʹ��pagehelper���
		PageHelper.startPage(pn, 5);
		//startPage���getAll��ѯ���Ƿ�ҳ��ѯ
		List<Employee> emps=employeeService.getAll();
		//��pageinfo��װ��Ϣ������5ҳ��������������ת��ҳ��������ʾ
		PageInfo<Employee> info=new PageInfo<>(emps,5);
		//����Ϣ�ŵ���������
		model.addAttribute("pageInfo",info);
		return "list";
	}
	
	@RequestMapping("/emps")
	//ע������json��ʽ
	@ResponseBody
	public Message getEmpsWithJson(
			@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model) 
	{
		PageHelper.startPage(pn, 5);
		List<Employee> emps=employeeService.getAll();
		PageInfo<Employee> info=new PageInfo<>(emps,5);
		//���￪ʼ��getEmps��ͬ
		return Message.success().add("pageInfo", info);
	}
	
	//����Ա��
	@RequestMapping(value = "/emp",method = RequestMethod.POST)
	@ResponseBody
	//@Validע��������ҪУ�飬BindingResult��װУ����
	public Message saveEmp(@Valid Employee employee,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			//У��ʧ�ܣ�����fail����ģ̬������ʾ������Ϣ
			Map<String, Object> map=new HashMap<String, Object>();
			List<FieldError> errors=bindingResult.getFieldErrors();
			for(FieldError error:errors) {
				map.put(error.getField(), error.getDefaultMessage());
			}
			return Message.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
			return Message.success();
		}
	}
	
	//�����û����Ƿ��ظ�
	@RequestMapping("/check")
	@ResponseBody
	public Message checkUser(@RequestParam("empName")String empName) {
		//���ж��Ƿ��ǺϷ��ı��ʽ
		//��������ж�ͨ��������ʱ�жϷǷ�
		String regex="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if(!empName.matches(regex)) {
			return Message.fail().add("va_msg","�û���������2-5λ���Ļ���6-16λӢ�ĺ����ֵ����");
		}
		//�����ݿ��û����ظ�У��
		boolean bool=employeeService.checkUser(empName);
		if(bool) {
			return Message.success();
		}
		else {
			return Message.fail().add("va_msg","�û����Ѵ���");
		}
	}
	
	//�޸ĵ�ҳ����Ҫ��ȡ��Ա����Ϣ
	@RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
	@ResponseBody
	//@PathVariableָ����·��ȡ����
	public Message getEmp(@PathVariable("id")Integer id) {
		Employee employee=employeeService.getEmp(id);
		return Message.success().add("emp", employee);
	}
	
	//Ա������
	//sql���Ϊwhere emp_id = #{empId,jdbcType=INTEGER}
	//��value = "/emp/{id}"����emp_id = null����id��ƥ��
	//ӦдΪvalue = "/emp/{empId}"
	@RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
	@ResponseBody
	public Message updateEmp(Employee employee) {
		employeeService.updateEmp(employee);
		return Message.success();
	}
	
	//ɾ������������������һ
	@RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public Message deleteEmp(@PathVariable("id")String id) {
		//���id����"-"�ַ�����������ɾ��
		if(id.contains("-")) {
			String[] ids=id.split("-");
			List<Integer> del_ids=new ArrayList<Integer>();
			for(String s:ids) {
				del_ids.add(Integer.parseInt(s));
			}
			employeeService.deleteBatch(del_ids);
		}
		else {
			employeeService.deleteEmp(Integer.parseInt(id));
		}
		return Message.success();
	}
}
