package lab.quarkus.product;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lab.quarkus.product.entities.Product;
import lab.quarkus.product.repositories.ProductRepository;

import java.util.List;

@Path("/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductApi {

  @Inject
  ProductRepository productRepository;


  @GET
  public List<Product> getProductList() {
    return productRepository.getProducts();
  }

  @POST
  public Response addProduct(Product product) {
    productRepository.save(product);
    return Response.ok().build();
  }


  @DELETE
  public Response deleteProduct(Product product) {
    productRepository.delete(product);
    return Response.ok().build();
  }
}
