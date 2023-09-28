package lab.quarkus.product.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lab.quarkus.product.entities.Product;

import java.util.List;

@ApplicationScoped
public class ProductRepository {

  @Inject
  EntityManager em;

  @Transactional
  public void save(Product product){
    em.persist(product);
  }

  @Transactional
  public void delete(Product product){
    em.remove(product);
  }

  @Transactional
  public List<Product> getProducts(){
    return em.createQuery("select p from Product p", Product.class).getResultList();
  }




}
