package br.com.socialmeli.controllers;

import br.com.socialmeli.dtos.SellerClientsCountDTO;
import br.com.socialmeli.dtos.SellerClientsListDTO;
import br.com.socialmeli.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SocialMeliController {

    private UserService userService = new UserService();

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity serguirVendedor(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
        try {
            userService.follow(userId, userIdToFollow);
            return new ResponseEntity(HttpStatus.OK);
            // Adicionar tartamento especifico de erro
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/{userId}/followers/count/")
    public  ResponseEntity<SellerClientsCountDTO>  countSeguidoresVendedor(@PathVariable Integer userId){
        SellerClientsCountDTO sellerClientsCountDTO = userService.contFollowersList(userId);
        return new ResponseEntity<>(sellerClientsCountDTO, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<SellerClientsListDTO> listSeguidoresVendedor(@PathVariable Integer userId){
        SellerClientsListDTO sellerDTO = userService.getFollowersList(userId);
        return new ResponseEntity<>(sellerDTO, HttpStatus.OK);
    }
}
