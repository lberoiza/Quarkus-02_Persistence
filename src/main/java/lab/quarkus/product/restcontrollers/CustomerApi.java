package lab.quarkus.product.restcontrollers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lab.quarkus.product.entities.Customer;
import lab.quarkus.product.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Path("/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerApi {

  @Inject
  CustomerRepository customerRepository;


  @GET
  public List<Customer> getCustomerList() {
    return customerRepository.getAll();
  }

  @GET()
  @Path("/{id}")
  public Customer getCustomer(@PathParam("id") Long id) {
    return customerRepository.findById(id).orElseGet(() -> {
      Customer c = new Customer();
      c.setName("Unknown Name");
      c.setSurname("Unknown Surname");
      return c;
    });
  }

  @PUT()
  @Path("/{id}")
  public Response updateProduct(@PathParam("id") Long id, Customer customer) {
    Optional<Customer> optionalCustomer = customerRepository.findById(id);
    if(optionalCustomer.isPresent()) {
      Customer customerToUpdate = optionalCustomer.get();
      customerToUpdate.updateWith(customer);
      customerRepository.update(customerToUpdate);
    }
    return Response.ok().build();
  }


  @POST
  public Response addCustomer(Customer customer) {
    customerRepository.save(customer);
    return Response.ok().build();
  }


  @DELETE
  public Response deleteProduct(Customer customer) {
    customerRepository.delete(customer);
    return Response.ok().build();
  }
}
