package com.fh.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.common.ServiceResponse;
import com.fh.mapper.EmpMapper;
import com.fh.model.Emp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Resource
    private EmpMapper empMapper;

    @Override
    public ServiceResponse add() {
        List<Emp> list=new ArrayList<Emp>();
        list.add(new Emp(null,"安其拉",25,"男",5000));
        list.add(new Emp(null,"妲己",15,"女",25000));
        list.add(new Emp(null,"亚瑟",35,"男",15000));
        list.add(new Emp(null,"典韦",55,"男",7000));
        for (Emp emp : list) {
            empMapper.insert(emp);
        }
        return ServiceResponse.success(list);
    }

    @Override
    public List<Emp> query() {
        return empMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public void insert(List<Emp> list) {
        empMapper.insertBatch(list);
    }
}
