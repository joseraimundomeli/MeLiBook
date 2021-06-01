package br.com.socialmeli.services;

import br.com.socialmeli.dtos.UserDTO;
import br.com.socialmeli.dtos.SellerClientsCountDTO;
import br.com.socialmeli.dtos.SellerClientsListDTO;
import br.com.socialmeli.models.User;
import br.com.socialmeli.resposistories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository = new UserRepository();

    @Override
    public Boolean follow(Integer idCliente, Integer idVendedor) {

        // Verifica se os id existem
        User client = userRepository.findById(idCliente);
        client.addConnection(idVendedor);

        return true;
    }

    public Boolean unfollow(Integer idCliente, Integer idVendedor) {

        // Verifica se os id existem
        User client = userRepository.findById(idCliente);
        client.removeConnection(idVendedor);

        return true;
    }

    public SellerClientsCountDTO contFollowersList(Integer userId){
        User user = userRepository.findById(userId);
        ArrayList<User> clientes = (ArrayList<User>) userRepository.loadUsuarios();
        long cont = clientes.stream().filter(x -> x.getFollow().contains(userId)).count();


        SellerClientsCountDTO vendedorDTO = new SellerClientsCountDTO();
        vendedorDTO.setUserName(user.getUserName());
        vendedorDTO.setIdUser(user.getUserId());
        vendedorDTO.setFollowers_count((int) cont);
        return vendedorDTO;
    }


    public SellerClientsListDTO getFollowersList(Integer userId){

        UserRepository userRepository = new UserRepository();
        ArrayList<User> users = (ArrayList<User>) userRepository.loadUsuarios();

        User user = userRepository.findById(userId);

        List<UserDTO> seguidores = users.stream()
                .filter(x -> x.getFollow().contains(userId))
                .map(y -> new UserDTO(y.getUserId(), y.getUserName()))
                .collect(Collectors.toList());

        // Montagem do DTO
        SellerClientsListDTO vendedorDTO = new SellerClientsListDTO();
        vendedorDTO.setUserId(user.getUserId());
        vendedorDTO.setUserName(user.getUserName());
        vendedorDTO.setFolloers(seguidores);

        return vendedorDTO;
    }
}
