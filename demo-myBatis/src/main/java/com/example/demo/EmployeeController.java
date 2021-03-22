package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Company;
import com.example.demo.domain.Employee;
import com.example.demo.mapper.CompanyMapper;
import com.example.demo.mapper.EmployeeMapper;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeMapper mapper;
	
	// 1 이 아닌 데이터를 전달하기 위해서 변경
	// 리턴타입을 Company로 변경
	@PostMapping("")
	public Employee post(@RequestBody Employee employee) {
		
		mapper.insert(employee);
		return employee;
	}
	
	@GetMapping("")
	public List<Employee> getAll() {
		return mapper.getAll();
	}
	
	@GetMapping("/{id}")
	public Employee get(@PathVariable("id")int id) {
		
		return mapper.getById(id);
	}
	
}
