package com.example.dd.springapp.controller;

import com.example.dd.springapp.model.cart.CartRepository;
import com.example.dd.springapp.model.product.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {

    @Mock
    CartRepository cartRepository;

    @Test
    @DisplayName("Should Throw IllegalArgumentException when no carts for given id ")
    void NoCartsForGivenIdThrowIllegalArgumentException() {
        //given-when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cartRepository.findById(1).orElseThrow(() -> new IllegalArgumentException("Not found Cart with id " + 1));
        });
        String expectedMessage = "Not found Cart with id " + 1;
        String actualMessage = exception.getMessage();
        //then
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Should Throw IllegalArgumentException when no products for given id ")
    void NoProductsForGivenIdThrowIllegalArgumentException() {
        //given-when
        ProductRepository productRepository = mock(ProductRepository.class);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productRepository.findById(1).orElseThrow(() -> new IllegalArgumentException("Not found Product with id " + 1));
        });
        String expectedMessage = "Not found Product with id " + 1;
        String actualMessage = exception.getMessage();
        //then
        assertTrue(actualMessage.contains(expectedMessage));
    }

}