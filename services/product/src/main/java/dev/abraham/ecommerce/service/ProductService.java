package dev.abraham.ecommerce.service;

import dev.abraham.ecommerce.exception.ProductPurchaseException;
import dev.abraham.ecommerce.mapper.ProductMapper;
import dev.abraham.ecommerce.model.Product;
import dev.abraham.ecommerce.repository.ProductRepository;
import dev.abraham.ecommerce.request.ProductPurchaseRequest;
import dev.abraham.ecommerce.request.ProductRequest;
import dev.abraham.ecommerce.response.ProductPurchaseResponse;
import dev.abraham.ecommerce.response.ProductResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest request) {
        Product product=productMapper.toProduct(request);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        List<Integer> productIds=request.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        List<Product> storedProducts=productRepository.findAllByIdInOrderById(productIds);
        if(storedProducts.size()!=productIds.size()){
            throw new ProductPurchaseException("One or more products does not exists");
        }

        List<ProductPurchaseRequest> storeRequest=request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        List<ProductPurchaseResponse> purchasedProducts=new ArrayList<>();
        for (int i = 0; i < storedProducts.size(); i++) {
            ProductPurchaseRequest productRequest=storeRequest.get(i);
            Product product=storedProducts.get(i);
            if(product.getAvailableQuantity()<productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient quantity");
            }
            double newAvailableQuantity=product.getAvailableQuantity()-productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(()->new EntityNotFoundException("Product not found"));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
