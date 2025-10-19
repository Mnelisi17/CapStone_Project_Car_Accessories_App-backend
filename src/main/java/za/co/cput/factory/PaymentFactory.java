package za.co.cput.factory;
/**
 * Hlumelo Madlingozi
 * 222648120
 */

import java.time.LocalDate;

import za.co.cput.domain.Payment;
import za.co.cput.util.Helper;

public class PaymentFactory {

    public static Payment createPayment(Long id, LocalDate paymentDate, String paymentMethod, Double amount,
                                        String status) {

        if (id == null || Helper.isNullOrInvalidDate(paymentDate) || Helper.isNullOrEmpty(paymentMethod)
                || Helper.isNegativeOrZero(amount) || Helper.isNullOrEmpty(status)) {
            return null;
        }

        return new Payment.Builder()
                .setId(id)
                .setPaymentDate(paymentDate)
                .setPaymentMethod(paymentMethod)
                .setAmount(amount)
                .setStatus(status)
                .build();
    }
}