package com.news.news.service;

import com.news.news.dto.ProductDTO;
import com.news.news.dto.ProductImageDTO;
import com.news.news.exception.DataNotFoundException;
import com.news.news.model.Category;
import com.news.news.model.Product;
import com.news.news.model.ProductImage;
import com.news.news.repositories.CategoryRepository;
import com.news.news.repositories.ProductImageRepository;
import com.news.news.repositories.ProductRepository;
import com.news.news.responses.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;

    @Override
    public Product creatproduct(ProductDTO productDTO) throws DataNotFoundException {
        Category existCategory = categoryRepository.findById(productDTO.getCategoryId()).
                orElseThrow(() -> new DataNotFoundException("can not found category" + productDTO.getCategoryId()));
        Product newProduct = Product.builder()
                .name(productDTO.getName())
                .thumbnail(productDTO.getThumbnail())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .category(existCategory)
                .build();
        return productRepository.save(newProduct);
    }

    @Override
    public Product getProductById(Long productId) throws DataNotFoundException {

        return productRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException("can not found product" + productId));
    }

    @Override
    public Page<ProductResponse> getAllProduct(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).map(product ->
        {
            ProductResponse productResponse = ProductResponse.builder()
                    .name(product.getName())
                    .price(product.getPrice())
                    .thumbnail(product.getThumbnail())
                    .description(product.getDescription())
                    .categoryId(product.getCategory().getId())
                    .build();
            productResponse.setCreatedAt(product.getCreatedAt());
            productResponse.setUpdateAt(product.getUpdatedAt());
            return productResponse;
        });

    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) throws DataNotFoundException {
        Product existProduct = getProductById(id);
        Category existCategory = categoryRepository.findById(productDTO.getCategoryId()).
                orElseThrow(() -> new DataNotFoundException("can not found category" + productDTO.getCategoryId()));
        if (existProduct != null) {
            existProduct.setName(productDTO.getName());
            existProduct.setPrice(productDTO.getPrice());
            existProduct.setThumbnail(productDTO.getThumbnail());
            existProduct.setDescription(productDTO.getDescription());
            existProduct.setCategory(existCategory);
            return productRepository.save(existProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        productRepository.delete(optionalProduct.get());
    }

    @Override
    public boolean existByName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    public ProductImage creatProductImage(Long productId,
                                          ProductImageDTO productImageDTO)
            throws Exception {
        Product existingProduct = productRepository
                .findById(productId)
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Cannot find product with id: " + productImageDTO.getProductId()));
        ProductImage newProductImage = ProductImage.builder()
                .product(existingProduct)
                .imageUrl(productImageDTO.getImageUrl())
                .build();
        //Ko cho insert quá 5 ảnh cho 1 sản phẩm
        int size = productImageRepository.findByProductId(productId).size();
        if (size >= 5) {
            throw new Exception("Number of images must be <= 5");
        }
        return productImageRepository.save(newProductImage);
    }

}
