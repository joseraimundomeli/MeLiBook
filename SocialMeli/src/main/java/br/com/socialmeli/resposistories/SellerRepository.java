package br.com.socialmeli.resposistories;

import br.com.socialmeli.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
