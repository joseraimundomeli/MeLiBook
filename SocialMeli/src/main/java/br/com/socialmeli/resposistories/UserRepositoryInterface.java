package br.com.socialmeli.resposistories;

import br.com.socialmeli.models.User;

import java.util.List;

public interface UserRepositoryInterface {
    List<User> loadUsuarios();

    User findById(Integer id);

    User updateUser(Integer id);
}
