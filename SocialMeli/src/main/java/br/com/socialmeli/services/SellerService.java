package br.com.socialmeli.services;

import br.com.socialmeli.dtos.*;
import br.com.socialmeli.exceptions.UserNotFound;
import br.com.socialmeli.models.Post;
import br.com.socialmeli.models.Product;
import br.com.socialmeli.models.Seller;
import br.com.socialmeli.resposistories.PostRepository;
import br.com.socialmeli.resposistories.ProductRepository;
import br.com.socialmeli.resposistories.SellerRepository;
import br.com.socialmeli.util.ProductToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepositoryInterface;

    @Autowired
    private PostRepository postRepositoryInterface;

    @Autowired
    private ProductRepository productRepository;

    // 0002
    public ResponseEntity<SellerClientsCountDTO> countFollowers(Integer userId){

        Optional<Seller> optionalSeller = sellerRepositoryInterface.findById((long) userId);

        if (!optionalSeller.isPresent()) {
            throw new UserNotFound("Seller not found for ID: " + userId);
        }

        Seller seller = optionalSeller.get();
        // Mount seller dto
        SellerClientsCountDTO sellerClientsCountDTO = new SellerClientsCountDTO();
        sellerClientsCountDTO.setUserName(seller.getUserName());
        sellerClientsCountDTO.setIdUser(seller.getUserId());
        sellerClientsCountDTO.setFollowers_count(seller.getFollowers().size());
        return new  ResponseEntity<SellerClientsCountDTO>(sellerClientsCountDTO,HttpStatus.OK);
    }


    public ResponseEntity countPromo(Integer userId) {
        Optional<Seller> optionalSeller = sellerRepositoryInterface.findById((long) userId);

        if (!optionalSeller.isPresent()) {
            throw new UserNotFound("Seller not found for ID: " + userId);
        }

        Seller seller = optionalSeller.get();
        Long count = seller.getPosts().stream().filter(x -> x.getHasPromo()).count();
        SellerCountPromoDTO sellerCountPromoDTO = new SellerCountPromoDTO();
        sellerCountPromoDTO.setUserId(seller.getUserId());
        sellerCountPromoDTO.setUserName(seller.getUserName());
        sellerCountPromoDTO.setPromoProductsCount(count);
        return new ResponseEntity<SellerCountPromoDTO>(sellerCountPromoDTO, HttpStatus.OK);
    }


    public ResponseEntity listFollowers(Integer userId, String order) {
        Optional<Seller> optionalSeller = sellerRepositoryInterface.findById((long) userId);

        if (!optionalSeller.isPresent()) {
            throw new UserNotFound("Seller not found for ID: " + userId);
        }

        Seller seller = optionalSeller.get();

        SellerClientsListDTO sellerClientsListDTO = new SellerClientsListDTO();

        sellerClientsListDTO.setUserId(seller.getUserId());
        sellerClientsListDTO.setUserName(seller.getUserName());

        List<UserDTO> followersList = seller.getFollowers()
                .stream().map(x -> x.getUser()).map(user -> new UserDTO(user.getUserId(), user.getUserName())).collect(Collectors.toList());

        if (order != null){
            if (order.equals("name_asc")){
                followersList = followersList.stream()
                        .sorted((o1, o2) -> o1.getUserName().compareTo(o2.getUserName()) < 0? -1 : 0)
                        .collect(Collectors.toList());
            }else if (order.equals("name_desc")){
                followersList = followersList.stream()
                        .sorted((o1, o2) -> o1.getUserName().compareTo(o2.getUserName()) > 0? -1 : 0)
                        .collect(Collectors.toList());
            }
        }

        sellerClientsListDTO.setFolloers(
                followersList
        );

        return new ResponseEntity<SellerClientsListDTO>(sellerClientsListDTO, HttpStatus.OK) ;
    }

    public ResponseEntity createPost(PostNewDTO postDTO) {

        Long userId = postDTO.getUserId();

        Optional<Seller> optionalSeller = sellerRepositoryInterface.findById((long) userId);

        if (!optionalSeller.isPresent()) {
            throw new UserNotFound("Seller not found for ID: " + userId);
        }

        Seller seller = optionalSeller.get();
        Product product = new Product();
        Post post = new Post();

        // melhorar esse parse
        post.setCategory(postDTO.getCategory());
        post.setDate(postDTO.getDate());
        post.setPrice(postDTO.getPrice());
        post.setHasPromo(postDTO.getHasPromo());
        post.setDiscount(postDTO.getDiscount());
        post.setDetail(product);
        post.setSeller(seller);
        post = postRepositoryInterface.save(post);

        product.setProductName(postDTO.getDetail().getProductName());
        product.setBrand(postDTO.getDetail().getBrand());
        product.setColor(postDTO.getDetail().getColor());
        product.setNotes(postDTO.getDetail().getNotes());
        product.setType(postDTO.getDetail().getType());
        product.setPost(post);

        seller.getPosts().add(post);

        sellerRepositoryInterface.save(seller);
        productRepository.save(product);

        return new ResponseEntity("Cadastrado com sucesso!", HttpStatus.OK);
    }

    public ResponseEntity listPromotions(Integer userId) {
        Optional<Seller> optionalSeller = sellerRepositoryInterface.findById((long) userId);

        if (!optionalSeller.isPresent()) {
            throw new UserNotFound("Seller not found for ID: " + userId);
        }

        Seller seller = optionalSeller.get();
        SellerPromoPostListDTO sellerResponse = new SellerPromoPostListDTO();

        List<Post> posts = seller.getPosts()
                .stream()
                .filter(x -> x.getHasPromo())
                .collect(Collectors.toList());

        List<PostDTO> postDTOS = posts
                .stream()
                .map( x -> new PostDTO(
                        x.getPostId(),
                        x.getDate(),
                        ProductToDTO.convertProdctToDTO(x.getDetail()),
                        x.getCategory(),
                        x.getPrice()
                ))
                .collect(Collectors.toList());

        sellerResponse.setUserName(seller.getUserName());
        sellerResponse.setUserId(seller.getUserId());
        sellerResponse.setPosts(postDTOS);
        return new ResponseEntity<SellerPromoPostListDTO>(sellerResponse, HttpStatus.OK);
    }
}
