package com.news.news.controller;

import com.news.news.dto.OtherDTO;
import com.news.news.exception.DataNotFoundException;
import com.news.news.responses.OrderListResponse;
import com.news.news.responses.OrderResponse;
import com.news.news.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/others")
@RequiredArgsConstructor
public class OderController {
    private final OrderService orderService;
    @PostMapping("")
    public ResponseEntity<?> creatOther(@Valid @RequestBody OtherDTO otherDTO, BindingResult result) {
        try {

            if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
            orderService.creatOrder(otherDTO);
            return ResponseEntity.ok("Post successful" + otherDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("bad");

        }


    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOther(@Valid @RequestBody OtherDTO otherDTO,
                                              @Valid @PathVariable Long id
                                                ,BindingResult result){

        return  ResponseEntity.ok("update other "+ otherDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOther(@PathVariable long id){

        return ResponseEntity.ok("delete other  with id "+ id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> getOther(@PathVariable long id) throws DataNotFoundException {
        OrderResponse orderResponse = orderService.getOder(id);
        return ResponseEntity.ok("get other  with id " + id + orderResponse);
    }

    @GetMapping()
    public ResponseEntity<?> getAllOrder(@RequestParam("page") int page,
                                         @RequestParam("limit") int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        Page<OrderResponse> orderResponses = orderService.getAllOrder(pageRequest);
        int totalPages = orderResponses.getTotalPages();
        List<OrderResponse> responses = orderResponses.getContent();
        return ResponseEntity.ok(OrderListResponse
                .builder()
                .orderList(responses)
                .totalPage(totalPages)
                .build());
    }
}

