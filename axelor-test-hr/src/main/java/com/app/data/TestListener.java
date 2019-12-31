package com.app.data;

import com.axelor.app.db.Test;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class TestListener {
  @PostPersist
  @PostUpdate
  private void onPostPersistOrUpdate(Test Test) {
    System.out.println("Test saved");
  }
}
