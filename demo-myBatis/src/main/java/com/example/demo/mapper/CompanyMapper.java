package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.Company;

// Mapper로 인식시켜주기 위한 어노테이션
@Mapper
public interface CompanyMapper {
	
	@Insert("insert into company ( company_name, company_address) values ( #{company.name}, #{company.address} ) ")
	@Options(useGeneratedKeys = true, keyProperty = "id") // 자동으로 생성되는 id값이 company 의 id값에 들어간 상태로 반환하게 된다.
	int insert(@Param("company") Company company);
	
	// Company의 모든 객체 반환
	@Select("select * from company")
	@Results(id="CompanyMap", value={
		@Result(property = "name", column = "company_name"),
		@Result(property = "address", column = "company_address"),
		@Result(property = "employeeList", column = "id", many = @Many(select = "com.example.demo.mapper.EmployeeMapper.getByCompanyId"))
	}) // 복수개의 propery 칼럼 매핑관계를 지정해줘야한다. id는 일치하므로 별도매핑이 필요없음.
	List<Company> getAll();
	
	@Select("select * from company where id = #{id}")
	@ResultMap("CompanyMap")
	Company getById(@Param("id") int id);
}
