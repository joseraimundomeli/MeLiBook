package br.com.socialmeli.services;

import br.com.socialmeli.dtos.*;
import br.com.socialmeli.exceptions.AutoFollowErro;
import br.com.socialmeli.exceptions.RelationshipError;
import br.com.socialmeli.exceptions.UserNotFound;
import br.com.socialmeli.models.*;
import br.com.socialmeli.resposistories.FollowRepository;
import br.com.socialmeli.resposistories.SellerRepository;
import br.com.socialmeli.resposistories.UserRepository;
import br.com.socialmeli.util.ConvertProductToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SocialMeliService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private FollowRepository followRepository;

    public ResponseEntity follow(Integer userId, Integer userIdToFollow) {

        Optional<Seller> optionalSeller =  sellerRepository.findById((long) userIdToFollow);
        Optional<User> optionalUser    =   userRepository.findById((long) userId);

        if (!optionalSeller.isPresent()) {
            throw new UserNotFound("Seller not found for ID: " + userIdToFollow);
        }

        if (!optionalUser.isPresent()) {
            throw new UserNotFound("User not found for ID: " + userId);
        }

        if (userId.equals(userIdToFollow)){
            throw new AutoFollowErro("You cannot follow yourself!");
        }

        Seller seller = optionalSeller.get();
        User user = optionalUser.get();


        Following following = new Following(user, seller);

        if (seller.getFollowers().contains(following)){
            throw new RelationshipError("User ID "+ userId+" already follows User ID " + userIdToFollow);
        }

        user.getFollowing().add(following);
        seller.getFollowers().add(following);

        followRepository.save(following);

        userRepository.save(user);

        sellerRepository.save(seller);

        return new ResponseEntity<>("Success!",HttpStatus.OK);
    }


    public ResponseEntity listFollowing(Integer userId, String order) {
        Optional<User> optionalUser = userRepository.findById((long) userId);

        if (!optionalUser.isPresent()) {
            throw new UserNotFound("User not found for ID: " + userId);
        }

        User user = optionalUser.get();

        UserSallersListDTO userSellersListDTO = new UserSallersListDTO();

        userSellersListDTO.setUserId(user.getUserId());
        userSellersListDTO.setUserName(user.getUserName());
        List<UserDTO> following =  user.getFollowing()
                .stream().map(x -> x.getSeller())
                .map(seller -> new UserDTO(seller.getUserId(), seller.getUserName()))
                .collect(Collectors.toList());

        if (order != null){
            if (order.equals("name_asc")){
                following = following.stream()
                        .sorted((o1, o2) -> o1.getUserName().compareToIgnoreCase(o2.getUserName()) < 0? -1 : 0)
                        .collect(Collectors.toList());
            }else if (order.equals("name_desc")){
                following = following.stream()
                        .sorted((o1, o2) -> o1.getUserName().compareToIgnoreCase(o2.getUserName()) > 0? -1 : 0)
                        .collect(Collectors.toList());
            }
        }

        userSellersListDTO.setFollowing(
                following
        );
        return new ResponseEntity<UserSallersListDTO>(userSellersListDTO, HttpStatus.OK) ;
    }

    public ResponseEntity unfollow(Integer userId, Integer userIdToUnfollow) {

        Optional<User> optionalUser = userRepository.findById((long) userId);
        Optional<Seller> optionalSeller = sellerRepository.findById((long) userIdToUnfollow);


        if (!optionalSeller.isPresent()) {
            throw new UserNotFound("Seller not found for ID: " + userIdToUnfollow);
        }

        if (!optionalUser.isPresent()) {
            throw new UserNotFound("User not found for ID: " + userId);
        }

        User user = optionalUser.get();
        Seller seller = optionalSeller.get();

        // verifica se é vendedor
        Following following = new Following(user, seller);
        if (!seller.getFollowers().contains(following)){
            throw new RelationshipError("Relationship between User ID: " +userId+ " and Uesr ID:" + userIdToUnfollow + " not found!");
        }

        user.getFollowing().remove(following);
        seller.getFollowers().remove(following);

        userRepository.save(user);

        sellerRepository.save(seller);

        followRepository.delete(following);

        return new ResponseEntity<>("Unfollow between User ID: " +userId+ " and Uesr ID:" + userIdToUnfollow + " success!",HttpStatus.OK);
    }

    public ResponseEntity listRecentPost(Integer userId, String order) {
        Optional<User> optionalUser = userRepository.findById((long) userId);

        if (!optionalUser.isPresent()) {
            throw new UserNotFound("User not found for ID: " + userId);
        }

        User user = optionalUser.get();

        LocalDate pastTime = LocalDate.now().minusWeeks(2);


        List<Post> posts = user.getFollowing()
                .stream()
                .map(f -> f.getSeller().getPosts())
                .flatMap(plist -> plist.stream())
                .filter(post -> post.getDate().isAfter(pastTime))
                .collect(Collectors.toList());


        List<PostDTO> postDTO = posts.stream().map(
                                x -> new PostDTO(
                                                x.getPostId(),
                                                x.getDate(),
                                                ConvertProductToDTO.convertProdctToDTO(x.getDetail()),
                                                x.getCategory(),
                                                x.getPrice()
                                        )
                        ).collect(Collectors.toList());

        if (order != null){
            if (order.equals("date_asc")){
                postDTO = postDTO.stream()
                        .sorted(Comparator.comparing(PostDTO::getDate))
                        .collect(Collectors.toList());
            }else if (order.equals("date_desc")){
                postDTO = postDTO.stream()
                        .sorted(Comparator.comparing(PostDTO::getDate).reversed())
                        .collect(Collectors.toList());
            }
        }

        UserRecentPostListDTO userRecentPostListDTO = new UserRecentPostListDTO();
        userRecentPostListDTO.setUserName(user.getUserName());
        userRecentPostListDTO.setUserId(user.getUserId());
        userRecentPostListDTO.setPosts(postDTO);

        return new ResponseEntity(userRecentPostListDTO, HttpStatus.OK);
    }


}
