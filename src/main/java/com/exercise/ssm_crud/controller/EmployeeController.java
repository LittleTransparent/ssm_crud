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
 * 	处理员工crud请求
 * @author 为所欲为
 *
 */
@Controller
public class EmployeeController {
	//需要调用service层的业务逻辑组件
	@Autowired
	EmployeeService employeeService;

	
	//处理自定义的emps请求
	@RequestMapping("/emps2")
	public String getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model) {
		//使用pagehelper插件
		PageHelper.startPage(pn, 5);
		//startPage后的getAll查询就是分页查询
		List<Employee> emps=employeeService.getAll();
		//用pageinfo包装信息，连续5页，这样可以让跳转的页数连续显示
		PageInfo<Employee> info=new PageInfo<>(emps,5);
		//把信息放到请求域中
		model.addAttribute("pageInfo",info);
		return "list";
	}
	
	@RequestMapping("/emps")
	//注明返回json格式
	@ResponseBody
	public Message getEmpsWithJson(
			@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model) 
	{
		PageHelper.startPage(pn, 5);
		List<Employee> emps=employeeService.getAll();
		PageInfo<Employee> info=new PageInfo<>(emps,5);
		//这里开始和getEmps不同
		return Message.success().add("pageInfo", info);
	}
	
	//保存员工
	@RequestMapping(value = "/emp",method = RequestMethod.POST)
	@ResponseBody
	//@Valid注明参数需要校验，BindingResult封装校验结果
	public Message saveEmp(@Valid Employee employee,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			//校验失败，返回fail并在模态框中显示错误信息
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
	
	//检验用户名是否重复
	@RequestMapping("/check")
	@ResponseBody
	public Message checkUser(@RequestParam("empName")String empName) {
		//先判断是否是合法的表达式
		//避免最初判断通过，保存时判断非法
		String regex="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if(!empName.matches(regex)) {
			return Message.fail().add("va_msg","用户名可以是2-5位中文或者6-16位英文和数字的组合");
		}
		//再数据库用户名重复校验
		boolean bool=employeeService.checkUser(empName);
		if(bool) {
			return Message.success();
		}
		else {
			return Message.fail().add("va_msg","用户名已存在");
		}
	}
	
	//修改等页面需要获取的员工信息
	@RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
	@ResponseBody
	//@PathVariable指定从路径取变量
	public Message getEmp(@PathVariable("id")Integer id) {
		Employee employee=employeeService.getEmp(id);
		return Message.success().add("emp", employee);
	}
	
	//员工更新
	//sql语句为where emp_id = #{empId,jdbcType=INTEGER}
	//若value = "/emp/{id}"，则emp_id = null，与id不匹配
	//应写为value = "/emp/{empId}"
	@RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
	@ResponseBody
	public Message updateEmp(Employee employee) {
		employeeService.updateEmp(employee);
		return Message.success();
	}
	
	//删除，单个、批量二合一
	@RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public Message deleteEmp(@PathVariable("id")String id) {
		//如果id内有"-"字符串，则批量删除
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
