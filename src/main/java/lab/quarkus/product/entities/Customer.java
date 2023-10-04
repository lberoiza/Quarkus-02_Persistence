package lab.quarkus.product.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "customers")
public class Customer {

  @jakarta.persistence.Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;
  private String name;
  private String surname;


  public void updateWith(Customer customer) {
    this.setName(customer.getName());
    this.setSurname(customer.getSurname());
  }

}
