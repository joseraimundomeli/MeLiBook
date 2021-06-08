package br.com.socialmeli.resposistories;

import br.com.socialmeli.models.Following;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Following, Long> {
}
