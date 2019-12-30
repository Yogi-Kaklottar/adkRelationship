/*
 * hello heder file
 */
package com.axelor.hr.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.hr.db.Task;

public class TaskRepository extends JpaRepository<Task> {

	public TaskRepository() {
		super(Task.class);
	}

	public Task findByName(String name) {
		return Query.of(Task.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

}

