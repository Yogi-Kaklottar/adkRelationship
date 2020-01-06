/*
 * hello heder file
 */
package com.axelor.hr.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.hr.db.Addres;

public class AddresRepository extends JpaRepository<Addres> {

  public AddresRepository() {
    super(Addres.class);
  }
}
