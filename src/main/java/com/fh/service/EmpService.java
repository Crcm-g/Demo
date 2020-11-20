package com.fh.service;

import com.fh.common.ServiceResponse;
import com.fh.model.Emp;

import java.util.List;

public interface EmpService {
    ServiceResponse add();

    List<Emp> query();

    void insert(List<Emp> list);
}
