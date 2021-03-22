package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.Employee;

@Mapper
public interface EmployeeMapper {

	@Insert("insert into employee (company_id, employee_name, employee_address) values (#{employee.companyId}, #{employee.name}, #{employee.address} ) ")
	@Options(useGeneratedKeys = true, keyProperty = "id") // 자동으로 생성되는 id값이 company 의 id값에 들어간 상태로 반환하게 된다.
	int insert(@Param("employee") Employee employee);
	
	// Company의 모든 객체 반환
	@Select("select * from employee")
	@Results(id="EmployeeMap", value={
		@Result(property = "name", column = "employee_name"),
		@Result(property = "address", column = "employee_address")
	}) // 복수개의 propery 칼럼 매핑관계를 지정해줘야한다. id는 일치하므로 별도매핑이 필요없음.
	List<Employee> getAll();
	
	@Select("select * from employee where id = #{id}")
	@ResultMap("EmployeeMap")
	Employee getById(@Param("id") int id);
	
	@Select("select * from employee where company_id = #{companyId}")
	@ResultMap("EmployeeMap")
	List<Employee> getByCompanyId(@Param("companyId")int companyId);
	
}
