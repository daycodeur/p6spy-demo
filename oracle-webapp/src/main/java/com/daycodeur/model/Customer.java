package com.daycodeur.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "Customer")
public class Customer {

  @Id
  @Column(name = "id")
  @GeneratedValue
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "country")
  private String country;
  @Column(name = "age")
  private Integer age;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(final String country) {
    this.country = country;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(final Integer age) {
    this.age = age;
  }

  public Customer() {
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Customer)) {
      return false;
    }
    final Customer user = (Customer) o;
    return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(country, user.country);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, country);
  }
}
