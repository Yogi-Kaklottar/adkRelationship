/*
 * hello heder file
 */
package com.axelor.hr.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.hr.db.Department;

public class DepartmentRepository extends JpaRepository<Department> {

  public DepartmentRepository() {
    super(Department.class);
  }

  public Department findByName(String name) {
    return Query.of(Department.class).filter("self.name = :name").bind("name", name).fetchOne();
  }
}
