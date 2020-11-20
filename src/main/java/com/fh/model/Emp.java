package com.fh.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_emp")
public class Emp {
    @TableId(type = IdType.INPUT)
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Emp() {
    }

    public Emp(Integer id,String name,  Integer age, String gender, Integer salary) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }
}
