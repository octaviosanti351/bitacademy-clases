package com.tests.example.controller;

import com.tests.example.business.Cart;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api
@RequestMapping("/v1/cart")
public class CartController {


    private final Cart cartSession;

    public CartController(Cart cartSession) {
        this.cartSession = cartSession;
    }

    @PostMapping("/add")
    public ResponseEntity addToCart(@ApiParam(value = "isbn", example = "23432432") @RequestParam(value = "isbn") final String isbn, @ApiParam(value = "quantity", example = "1") @RequestParam(value = "quantity") final int quantity){
        Map<String, Object> response = new HashMap<>();

        try {
            cartSession.add(isbn, quantity);
            response.put("products", cartSession.getProducts());
            response.put("total", cartSession.total());
            return ResponseEntity.ok(response);
        }
        catch (Exception ex){
            ex.printStackTrace();
            response.put("error", ex.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

}
