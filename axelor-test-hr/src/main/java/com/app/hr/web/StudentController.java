package com.app.hr.web;

import com.axelor.hr.db.Student;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class StudentController {

  public void setResult(ActionRequest request, ActionResponse response) {
    Student s = request.getContext().asType(Student.class);
    s.setTotal(s.getMarks1() + s.getMarks2() + s.getMarks3());
    java.math.BigDecimal n;
    Double no = (double) (s.getTotal() / 3);
    n = new java.math.BigDecimal(no.toString());
    s.setPersantage(n);
    if (no >= 70) {
      s.setGrade("Distiction");
    } else if (70 < no && 60 >= no) {
      s.setGrade("firstClass");
    } else if (60 < no && 50 >= no) {
      s.setGrade("second class");
    } else if (60 < no && 50 >= no) {
      s.setGrade("passClass");
    } else {
      s.setGrade("fail");
    }

    System.out.println(s.toString());
    response.setValues(s);
    // response.setAlert("Total=" + s.getTotal() + "  Persantage=" + no + "   Grade" +
    // s.getGrade());
    // return;
    //		response.setValue("total", n);
  }
}
