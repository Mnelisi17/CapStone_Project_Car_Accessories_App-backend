package za.co.cput.service;

import za.co.cput.domain.Payment;

import java.util.Optional;

public interface IPaymentService extends IService<Payment, Long>{
    Optional<Payment> findByStatus(String status);
}