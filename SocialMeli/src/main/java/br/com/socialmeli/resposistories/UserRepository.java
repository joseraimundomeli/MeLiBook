package br.com.socialmeli.resposistories;

import br.com.socialmeli.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
