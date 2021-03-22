package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Company;
import com.example.demo.mapper.CompanyMapper;
import com.example.demo.service.CompanyService;

// API
// Rest 형식으로 작성
@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	private CompanyMapper mapper;
	
	@Autowired
	private CompanyService service;
	
	// 1 이 아닌 데이터를 전달하기 위해서 변경
	// 리턴타입을 Company로 변경
	@PostMapping("")
	public Company post(@RequestBody Company company) throws Exception {
		service.addCompany(company);
//		mapper.insert(company);
		return company;
	}
	
	@GetMapping("")
	public List<Company> getAll() {
		return mapper.getAll();
	}
	
	@GetMapping("/{id}")
	public Company get(@PathVariable("id")int id) {
		
		return mapper.getById(id);
	}
}
