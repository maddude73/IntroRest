package com.example.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerDTO {
  public String name;
  public String address;
  public Integer credit;

  public CustomerDTO() {} // MUST HAVE THIS FOR JAX-B
  public CustomerDTO(String name, String address, Integer credit) {
    this.name = name;
    this.address = address;
    this.credit = credit;
  }

  @Override
  public String toString() {
    return "CustomerDTO{" +
        "name='" + name + '\'' +
        ", address='" + address + '\'' +
        ", credit=" + credit +
        '}';
  }
}
