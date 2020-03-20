package com.exercise.ssm_crud.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//此处lombik注解为自己添加
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private Integer deptId;

    private String deptName;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }
}