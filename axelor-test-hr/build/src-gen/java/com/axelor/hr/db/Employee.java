/*
 * hello heder file
 */
package com.axelor.hr.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.HashKey;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;

@Entity
@Cacheable
@Table(
  name = "HR_EMPLOYEE",
  indexes = {@Index(columnList = "department"), @Index(columnList = "addres")}
)
public class Employee extends AuditableModel {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HR_EMPLOYEE_SEQ")
  @SequenceGenerator(name = "HR_EMPLOYEE_SEQ", sequenceName = "HR_EMPLOYEE_SEQ", allocationSize = 1)
  private Long id;

  @HashKey
  @NotNull
  @Column(unique = true)
  private String name;

  private LocalDate dateofbirth;

  @Min(18)
  @Max(55)
  private Integer age = 0;

  @Widget(image = true, title = "photo")
  @Lob
  @Basic(fetch = FetchType.LAZY)
  private byte[] photo;

  private LocalDate curruntdate;

  @Widget(massUpdate = true)
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {CascadeType.PERSIST, CascadeType.MERGE}
  )
  private Department department;

  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "employee",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<Laptop> laptop;

  @ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {CascadeType.PERSIST, CascadeType.MERGE}
  )
  private Set<Task> task;

  @OneToOne(
    fetch = FetchType.LAZY,
    cascade = {CascadeType.PERSIST, CascadeType.MERGE}
  )
  private Addres addres;

  @Widget(title = "Attributes")
  @Basic(fetch = FetchType.LAZY)
  @Type(type = "json")
  private String attrs;

  public Employee() {}

  public Employee(String name) {
    this.name = name;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getDateofbirth() {
    return dateofbirth;
  }

  public void setDateofbirth(LocalDate dateofbirth) {
    this.dateofbirth = dateofbirth;
  }

  public Integer getAge() {
    return age == null ? 0 : age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public byte[] getPhoto() {
    return photo;
  }

  public void setPhoto(byte[] photo) {
    this.photo = photo;
  }

  public LocalDate getCurruntdate() {
    return curruntdate;
  }

  public void setCurruntdate(LocalDate curruntdate) {
    this.curruntdate = curruntdate;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public List<Laptop> getLaptop() {
    return laptop;
  }

  public void setLaptop(List<Laptop> laptop) {
    this.laptop = laptop;
  }

  /**
   * Add the given {@link Laptop} item to the {@code laptop}.
   *
   * <p>It sets {@code item.employee = this} to ensure the proper relationship.
   *
   * @param item the item to add
   */
  public void addLaptop(Laptop item) {
    if (getLaptop() == null) {
      setLaptop(new ArrayList<>());
    }
    getLaptop().add(item);
    item.setEmployee(this);
  }

  /**
   * Remove the given {@link Laptop} item from the {@code laptop}.
   *
   * @param item the item to remove
   */
  public void removeLaptop(Laptop item) {
    if (getLaptop() == null) {
      return;
    }
    getLaptop().remove(item);
  }

  /**
   * Clear the {@code laptop} collection.
   *
   * <p>If you have to query {@link Laptop} records in same transaction, make sure to call {@link
   * javax.persistence.EntityManager#flush() } to avoid unexpected errors.
   */
  public void clearLaptop() {
    if (getLaptop() != null) {
      getLaptop().clear();
    }
  }

  public Set<Task> getTask() {
    return task;
  }

  public void setTask(Set<Task> task) {
    this.task = task;
  }

  /**
   * Add the given {@link Task} item to the {@code task}.
   *
   * @param item the item to add
   */
  public void addTask(Task item) {
    if (getTask() == null) {
      setTask(new HashSet<>());
    }
    getTask().add(item);
  }

  /**
   * Remove the given {@link Task} item from the {@code task}.
   *
   * @param item the item to remove
   */
  public void removeTask(Task item) {
    if (getTask() == null) {
      return;
    }
    getTask().remove(item);
  }

  /** Clear the {@code task} collection. */
  public void clearTask() {
    if (getTask() != null) {
      getTask().clear();
    }
  }

  public Addres getAddres() {
    return addres;
  }

  public void setAddres(Addres addres) {
    this.addres = addres;
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
    if (!(obj instanceof Employee)) return false;

    final Employee other = (Employee) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    if (!Objects.equals(getName(), other.getName())) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(1258113742, this.getName());
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", getId())
        .add("name", getName())
        .add("dateofbirth", getDateofbirth())
        .add("age", getAge())
        .add("curruntdate", getCurruntdate())
        .omitNullValues()
        .toString();
  }
}
