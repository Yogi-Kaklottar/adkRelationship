package com.app.data;

import com.axelor.app.db.Demo;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class DemoListener {
  @PostPersist
  @PostUpdate
  private void onPostPersistOrUpdate(Demo demo) {
    System.out.println("Demo saved");
  }
}
