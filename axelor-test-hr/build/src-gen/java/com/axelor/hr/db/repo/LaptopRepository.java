/*
 * hello heder file
 */
package com.axelor.hr.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.hr.db.Laptop;

public class LaptopRepository extends JpaRepository<Laptop> {

	public LaptopRepository() {
		super(Laptop.class);
	}

	public Laptop findByName(String name) {
		return Query.of(Laptop.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

}

