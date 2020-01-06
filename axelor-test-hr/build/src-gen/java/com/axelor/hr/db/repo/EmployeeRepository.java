/*
 * hello heder file
 */
package com.axelor.hr.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.hr.db.Employee;

public class EmployeeRepository extends JpaRepository<Employee> {

  public EmployeeRepository() {
    super(Employee.class);
  }

  public Employee findByName(String name) {
    return Query.of(Employee.class).filter("self.name = :name").bind("name", name).fetchOne();
  }
}
