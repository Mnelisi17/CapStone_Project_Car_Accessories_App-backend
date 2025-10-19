package za.co.cput.controller;

/**
 * OrderController.java
 * Author: Kholiwe Faith Mafenuka (221686584)
 * Group: 3F
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import za.co.cput.domain.Order;
import za.co.cput.service.IOrderService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/order")
public class OrderController {

    private final IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

        @PreAuthorize("hasRole('BUYER')")
    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return orderService.save(order);
    }

    @PreAuthorize("hasAnyRole('ADMIN','BUYER')")
    @PostMapping("/create/{buyerId}")
    public Order createOrderForBuyer(@PathVariable Long buyerId, @RequestBody Order order) {
        return orderService.createOrderForBuyer(buyerId, order);
    }

    @PreAuthorize("hasAnyRole('ADMIN','BUYER')")
    @GetMapping("/read/{orderId}")
    public Order readOrder(@PathVariable Long orderId) {
        return orderService.read(orderId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public Order updateOrder(@RequestBody Order order) {
        return orderService.update(order);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        Order sampleOrder = new Order.Builder()
                .setOrderId(1L)
                .setTotalAmount(100.0)
                .setStatus("PAID")
                .setOrderDate(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(List.of(sampleOrder));
    }


    @PreAuthorize("hasAnyRole('ADMIN','BUYER')")
    @GetMapping("/buyer/{buyerId}")
    public List<Order> getOrdersByBuyer(@PathVariable Long buyerId) {
        return orderService.findByBuyerId(buyerId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/status/{status}")
    public List<Order> getOrdersByStatus(@PathVariable String status) {
        return orderService.findByStatus(status);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/status/update/{orderId}")
    public Order updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        return orderService.updateOrderStatus(orderId, status);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pending")
    public List<Order> getPendingOrders() {
        return orderService.findPendingOrders();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteById(orderId);
    }

}