package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Company;
import com.example.demo.mapper.CompanyMapper;
import com.example.demo.mapper.EmployeeMapper;

@Service
public class CompanyService {

	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public List<Company> getAll() {
		
		List<Company> companyList = companyMapper.getAll();
		if( companyList != null && companyList.size() > 0) {
			companyList.forEach(company -> {
				company.setEmployeeList(employeeMapper.getByCompanyId(company.getId()));
			});
		}
		
		return companyList;
	}
	
	//트랜잭션 추가설정작업
	@Transactional(rollbackFor = Exception.class)
	public Company addCompany(Company company) throws Exception{
		companyMapper.insert(company);
		if(true) {
			throw new RuntimeException("error...");
		}
		
		return company;
	}
	
}
