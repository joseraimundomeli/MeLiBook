package br.com.socialmeli.util;

import br.com.socialmeli.dtos.ProductDTO;
import br.com.socialmeli.models.Product;
import org.modelmapper.ModelMapper;

public class ConvertProductToDTO {


    public static ProductDTO convertProdctToDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        if (product == null){
            return null;
        }
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setBrand(product.getBrand());
        productDTO.setColor(product.getColor());
        productDTO.setNotes(product.getNotes());
        productDTO.setType(product.getType());
        return  productDTO;
    }
}
