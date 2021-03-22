package com.example.demo.domain;

import java.util.List;

import lombok.Data;

@Data
public class Company {
	// 3개의 프로퍼티를 갖는 클래스 생성
	private int id;
	private String name;
	private String address;
	private List<Employee> employeeList;
}
