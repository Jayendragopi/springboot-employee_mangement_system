package com.jay.springboot.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.springboot.entities.Employee;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
  JdbcTemplate template;
  
  @PostMapping("/add")
  public String addEmployee(@RequestBody Employee e) {
      String sql = "insert into employees (name, age, salary, position) values(?,?,?,?)";
      template.update(sql, e.getName(), e.getAge(), e.getSalary(), e.getPosition());
      return "New employee added successfully";
  }
  
  @GetMapping("/getAll")
  public List<Map<String, Object>> getAllEmployees() {
      String sql = "select * from employees";
      return template.queryForList(sql);
  }
  
  @GetMapping("{id}")
  public Map<String, Object> getById(@PathVariable int id){
	  String sql="select * from employees where id=?";
	  return template.queryForMap(sql,id);
  }
  
  @PutMapping("update/{id}")
  public String updateById(@PathVariable int id,@RequestBody Employee e) {
	  String sql="update employees set name=?,age=?,salary=?,position=? where id=?";
	  template.update(sql,e.getName(),e.getAge(),e.getSalary(),e.getPosition(),id);
	  return "Employee update successfully";
  }
  
  @DeleteMapping("delete/{id}")
  public String deleteById(@PathVariable int id) {
	  String sql="delete from employees where id=?";
	  template.update(sql,id);
	  return"employee deleted successfully";
  }
}
