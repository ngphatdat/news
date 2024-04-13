package com.news.news.service;

import com.news.news.dto.OtherDTO;
import com.news.news.exception.DataNotFoundException;
import com.news.news.model.Order;
import com.news.news.model.OrderStatus;
import com.news.news.model.User;
import com.news.news.repositories.OrderRepository;
import com.news.news.repositories.UserRepository;
import com.news.news.responses.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Order creatOrder(OtherDTO orderDTO) throws DataNotFoundException {
        User existUser = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("Can not found user id"
                        + orderDTO.getUserId()));
        //convert orderDTO => Order
        //dùng thư viện Model Mapper
        // Tạo một luồng bảng ánh xạ riêng để kiểm soát việc ánh xạ
        modelMapper.typeMap(OtherDTO.class, Order.class)
                .addMappings(mapper -> mapper.skip(Order::setId));
        // Cập nhật các trường của đơn hàng từ orderDTO
        Order order = new Order();
        modelMapper.map(orderDTO, order);
        order.setUser(existUser);
        order.setOrderDate(new Date());//lấy thời điểm hiện tại
        order.setStatus(OrderStatus.PENDING);
        //Kiểm tra shipping date phải >= ngày hôm nay
        LocalDate shippingDate = orderDTO.getShippingDate() == null
                ? LocalDate.now() : orderDTO.getShippingDate();
        if (shippingDate.isBefore(LocalDate.now())) {
            throw new DataNotFoundException("Date must be at least today !");
        }
        order.setShippingDate(shippingDate);
        order.setActive(true);
        orderRepository.save(order);
        return order;
    }

    @Override
    public OrderResponse getOder(Long id) throws DataNotFoundException {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("can not found order" + id));

        OrderResponse orderResponse = new OrderResponse();
        modelMapper.map(order, orderResponse);
        return orderResponse;
    }

    @Override
    public Page<OrderResponse> getAllOrder(PageRequest pageRequest) {
        return orderRepository.findAll(pageRequest).map(
                order -> {
                    OrderResponse orderResponse = OrderResponse.builder()
                            .fullName(order.getFullName())
                            .email(order.getEmail())
                            .address(order.getAddress())
                            .trackingNumber(order.getTrackingNumber())
                            .paymentMethod(order.getPaymentMethod())
                            .shippingMethod(order.getShippingMethod())
                            .note(order.getNote())
                            .address(order.getAddress())
                            .phoneNumber(order.getPhoneNumber())
                            .status(order.getStatus())
                            .shippingAddress(order.getShippingAddress())
                            .totalMoney(order.getTotalMoney())
                            .id(order.getId())
                            .build();
                    orderResponse.setActive(true);
                    orderResponse.setUser_id(order.getUser());
                    return orderResponse;
                }
        );
    }

    @Override
    public OrderResponse deleteOrder(Long id) {
        return null;
    }
}
