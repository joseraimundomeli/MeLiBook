package br.com.socialmeli.controllers;

import br.com.socialmeli.dtos.UserDTO;
import br.com.socialmeli.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/system/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @PostMapping
    public ResponseEntity createSeller(@RequestBody UserDTO userDTO){
        return sellerService.insertSeller(userDTO);
    }

    @GetMapping
    public ResponseEntity listSeller(){
        return sellerService.listSeller();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable Integer userId){
        return sellerService.deleteSeller(userId);
    }
}
