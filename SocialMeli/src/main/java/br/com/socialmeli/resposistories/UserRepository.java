package br.com.socialmeli.resposistories;

import br.com.socialmeli.TempTest;
import br.com.socialmeli.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements UserRepositoryInterface {
    private static final String DATASET_PATH = "src/data/database.json";

    @Override
    public List<User> loadUsuarios() {
        List<User> users =  TempTest.users;
        return users;
    }

    @Override
    public User findById(Integer userId) {
        List<User> users = TempTest.users;
        User user = users.stream().filter(x -> x.getUserId().equals(userId)).findFirst().get();
        return user;
    }

    @Override
    public User updateUser(Integer userId) {
        List<User> users = TempTest.users;
        User user = users.stream().filter(x -> x.getUserId().equals(userId)).findFirst().get();
        // fazer atualização
        users.add(userId, user);
        return user;
    }
}
