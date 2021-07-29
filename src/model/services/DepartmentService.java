package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {
	
	public List<Department> findAll(){
		List<Department> list = new ArrayList<>();
		list.add(new Department(1,"Frutas"));
		list.add(new Department(2,"Laticinios"));
		list.add(new Department(3,"Gordura trans"));
		return list;
	}

}
