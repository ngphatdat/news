package com.news.news.service;

import com.news.news.dto.ProductDTO;
import com.news.news.dto.ProductImageDTO;
import com.news.news.exception.DataNotFoundException;
import com.news.news.model.Product;
import com.news.news.model.ProductImage;
import com.news.news.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IProductService {
    Product creatproduct(ProductDTO productDTO) throws DataNotFoundException;

    Product getProductById(Long productId) throws DataNotFoundException;

    Page<ProductResponse> getAllProduct(PageRequest pageRequest);

    Product updateProduct(Long id, ProductDTO productDTO) throws DataNotFoundException;

    void deleteProduct(Long id);

    boolean existByName(String name);

    ProductImage creatProductImage(Long productId,
                                   ProductImageDTO productImageDTO) throws Exception;
}
