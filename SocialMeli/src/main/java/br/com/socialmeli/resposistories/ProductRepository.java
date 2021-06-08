package br.com.socialmeli.resposistories;

import br.com.socialmeli.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
