package com.okan.swagger.controller;

import com.okan.swagger.domain.Product;
import com.okan.swagger.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Api(value = "onlinestore", description = "Operations pertaining to products in Online Store")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/list", method= RequestMethod.GET)
    @ApiOperation(value = "View a list of available products", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public Iterable list(){
        return productService.listAllProducts();
    }

    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET)
    public Product showProduct(@PathVariable Integer id){
        return productService.getProductById(id);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> saveProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return new ResponseEntity<>("Product saved successfully", HttpStatus.OK);
    }


    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateProduct(@PathVariable Integer id, @RequestBody Product product){
        Product storedProduct = productService.getProductById(id);
        storedProduct.setDescription(product.getDescription());
        storedProduct.setImageUrl(product.getImageUrl());
        storedProduct.setPrice(product.getPrice());
        productService.saveProduct(storedProduct);
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
    }


    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Integer id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);

    }
}
