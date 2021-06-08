package br.com.socialmeli.controllers;

import br.com.socialmeli.dtos.PostNewDTO;
import br.com.socialmeli.exceptions.PageNotFound;
import br.com.socialmeli.services.SellerService;
import br.com.socialmeli.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SocialMeliController {

    @Autowired
    private UserService userService;

    @Autowired
    private SellerService sellerService;

    // 0001
    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
        return userService.follow(userId, userIdToFollow);
    }

    //0002
    @GetMapping("/users/{userId}/followers/count/")
    public ResponseEntity followersCount(@PathVariable Integer userId){
        return sellerService.countFollowers(userId);
    }

    //0003
    //0008-followers
    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity sellerListFollowers(@PathVariable Integer userId, @RequestParam(value="order", required = false) String order){
        return sellerService.listFollowers(userId, order);
    }

    //0004
    //0008-followed
    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity clientListSellers(@PathVariable Integer userId, @RequestParam(value="order", required = false) String order){
        return  userService.listFollowing(userId, order);
    }

    // 0005
    @PostMapping("/products/newpost")
    public ResponseEntity createPost(@RequestBody PostNewDTO post){
        return sellerService.createPost(post);
    }

    // 0006
    // 0009
    @GetMapping("/products/followed/{userId}/list")
    public ResponseEntity listRecentPost(@PathVariable Integer userId, @RequestParam(value="order", required = false) String order){
        return userService.listRecentPost(userId, order);
    }


    // 0007
    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity unfollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow){
        return userService.unfollow(userId, userIdToUnfollow);
    }

    //0010
    @PostMapping("/products/newpromopost")
    public ResponseEntity creatPromotion(@RequestBody PostNewDTO postPromo){
        return sellerService.createPost(postPromo);
    }

    //0011
    @GetMapping("/products/{userId}/countpromo")
    public ResponseEntity countSellerPromo(@PathVariable Integer userId){
        return sellerService.countPromo(userId);
    }

    //0012
    @GetMapping("/products/{userId}/list/")
    public ResponseEntity listPromoProductsSeller(@PathVariable Integer userId){
        return sellerService.listPromotions(userId);
    }

    // Test do servidor
    @GetMapping("/**")
    public String testeServidor(){
        throw new PageNotFound("Sorry :( page not found!");
    }
}
