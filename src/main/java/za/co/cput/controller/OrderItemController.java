package za.co.cput.controller;
/*
 ** Kholiwe Faith Mafenuka
 ** 221686584
 ** Group 3F
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import za.co.cput.domain.OrderItem;
import za.co.cput.service.OrderItemService;

import java.util.List;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    private OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','BUYER')")
    @PostMapping("/create")
    public OrderItem createOrderItem(@RequestBody OrderItem itemId) {
        return orderItemService.save(itemId);
    }

    @PreAuthorize("hasAnyRole('ADMIN','BUYER')")
    @GetMapping("/read/{orderItemId}")
    public OrderItem readOrderItem(@PathVariable("orderItemId") long orderItemId) {
        return orderItemService.findByOrderItemId(orderItemId).stream().findFirst().orElse(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public OrderItem updateOrderItem(@RequestBody OrderItem item) {
        return orderItemService.save(item);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/All")
    public List<OrderItem> getAllItems() {
        return orderItemService.findAll();
    }

}
