package com.exercise.ssm_crud.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//此处lombik注解为自己添加
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer empId;
    
    //@Pattern表示自定义校验，regexp为自定义规则，message为报错信息
    @Pattern(regexp = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})",
    		message = "用户名可以是2-5位中文或者6-16位英文和数字的组合")
    private String empName;

    private String gender;
    
    //@Email表示校验合法email
    @Email
    private String email;

    private Integer dId;
    
    private Department department;//(和get、set一起)新加的
    
    public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }
    
    //生成有参方法
	public Employee(Integer empId, String empName, String gender, String email, Integer dId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.gender = gender;
		this.email = email;
		this.dId = dId;
	}
}