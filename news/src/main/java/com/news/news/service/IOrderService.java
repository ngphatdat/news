package com.news.news.service;

import com.news.news.dto.OtherDTO;
import com.news.news.exception.DataNotFoundException;
import com.news.news.model.Order;
import com.news.news.responses.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IOrderService {
    Order creatOrder(OtherDTO orderDTO) throws DataNotFoundException;

    OrderResponse getOder(Long id) throws DataNotFoundException;

    Page<OrderResponse> getAllOrder(PageRequest pageRequest);

    OrderResponse deleteOrder(Long id);


}
