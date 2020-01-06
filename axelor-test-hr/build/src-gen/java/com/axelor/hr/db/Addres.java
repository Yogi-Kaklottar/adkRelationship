/*
 * hello heder file
 */
package com.axelor.hr.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Cacheable
@Table(
  name = "HR_ADDRES",
  indexes = {@Index(columnList = "street")}
)
public class Addres extends AuditableModel {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HR_ADDRES_SEQ")
  @SequenceGenerator(name = "HR_ADDRES_SEQ", sequenceName = "HR_ADDRES_SEQ", allocationSize = 1)
  private Long id;

  @NameColumn private String street;

  private String area;

  private String pin;

  private LocalDateTime entrytime;

  @OneToOne(
    fetch = FetchType.LAZY,
    mappedBy = "addres",
    cascade = {CascadeType.PERSIST, CascadeType.MERGE}
  )
  private Employee employee;

  @Widget(title = "Attributes")
  @Basic(fetch = FetchType.LAZY)
  @Type(type = "json")
  private String attrs;

  public Addres() {}

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  public LocalDateTime getEntrytime() {
    return entrytime;
  }

  public void setEntrytime(LocalDateTime entrytime) {
    this.entrytime = entrytime;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public String getAttrs() {
    return attrs;
  }

  public void setAttrs(String attrs) {
    this.attrs = attrs;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (this == obj) return true;
    if (!(obj instanceof Addres)) return false;

    final Addres other = (Addres) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return false;
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", getId())
        .add("street", getStreet())
        .add("area", getArea())
        .add("pin", getPin())
        .add("entrytime", getEntrytime())
        .omitNullValues()
        .toString();
  }
}
