package com.atguigu.springcloud.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.atguigu.springcloud.entities.Dept;

@FeignClient(value = "MICROSERVICECLOUD-DEPT", fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {
	
	@GetMapping("/dept/get/{id}")
	public Dept get(@PathVariable("id") long id);

	@GetMapping("/dept/list")
	public List<Dept> list();

	@GetMapping("/dept/add")
	public boolean add(Dept dept);
}
