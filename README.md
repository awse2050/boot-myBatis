# boot-myBatis

스프링 부트에서 MyBatis 설정연습

> 실무에서 쓰이는 방식

----------------------

**사용기술**
+ Spring Boot
+ MyBatis
+ MySQL

------------------------------


**추가적으로 알아둬야 하는 부분**

+ @Options 
```
        @Insert("insert into company ( company_name, company_address) values ( #{company.name}, #{company.address} ) ")
	@Options(useGeneratedKeys = true, keyProperty = "id") 
	int insert(@Param("company") Company company);
```
MySQL은 autoIncreament 속성으로 PK값을 INT로 지정하여 자동으로 값을 매겨 줄 수 있다.

Insert를 할 경우 결과값을 0 , 1 을 반환하기 떄문에

실무에서 API를 작성 할 경우 Insert를 헀을 때 사용자가 입력한 데이터를 볼수 있게 해줘야 하므로

Options를 통해 자동으로 생성되는 번호를 company의 "id" 칼럼에 입력되도록 할 수 있다.
```
	@PostMapping("")
	public Company post(@RequestBody Company company) throws Exception {

  	  mapper.insert(company);
		return company;
	}
```


--------------------------

+ @Results 및 관련 속성

DB 테이블의 칼럼과 JAVA의 Property가 다를 때 두 정보를 매핑시켜주기 위해서 사용한다.

```
@Select("select * from company")
@Results(id="CompanyMap", value={
  @Result(property = "name", column = "company_name"),
  @Result(property = "address", column = "company_address"),
  @Result(property = "employeeList", column = "id", many = @Many(select = "com.example.demo.mapper.EmployeeMapper.getByCompanyId"))
}) 
List<Company> getAll();

```
```
@Results(
  @Result(property = "name", column = "company_name"),
  @Result(property = "address", column = "company_address"),
  @Result(property = "employeeList", column = "id", many = @Many(select = "com.example.demo.mapper.EmployeeMapper.getByCompanyId"))
) 

```

위 아래의 코드는 동일하지만 다른점이 있다.

위 코드의 경우 `@Results` 의 `id` 속성은 해당 매핑을 재사용 하기위함이다. 이떄 `value` 속성의 값으로 매핑한 데이터의 정보를 넣어줘야한다.

아래 코드는 같은코드를 반복해서 사용해야 하기 떄문에 객체지향적이지 못하다. 

***그러므로 항상 위 코드형태로 사용하는 것이 좋다***

***재사용 방법***
```
@Select("select * from company where id = #{id}")
@ResultMap("CompanyMap")
Company getById(@Param("id") int id);
```

`property` 속성 값은 Java에 설정한 VO( Entity )의 변수를 입력하고 `column`의 경우 DB테이블의 칼럼명을 입력하여

서로 같은 데이터임을 알려 매핑을 시켜준다.

***`many` 속성을 사용한 @Result는 다른 @Result와 사용방식이 다르다.***

`property` - 매핑시키고자 하는 변수명
 
 `column` - `@Many`의 `select`에 지정된 메서드로 사용되는 파라미터
 
 `many` - `property`와 매핑 시켜주는 데이터를 반환할 메서드(Mapper)


---------------------------------------
+ @Transactional - rollbackFor 속성

트랜잭션 작업 시 Runtime예외일 때 롤백이 적용되는데 rollbackFor 속성의 value값으로 특정 예외클래스를 입력하면 해당 예외발생시에도 롤백이 이루어진다.

**지정한 예외클래스를 상속받는 모든 클래스도 포함된다**


### 출처 

[스프링부트 - MyBatis 이거 하나로 끝내자 ! ! !](https://www.youtube.com/watch?v=4YOk7oLGTKI)
