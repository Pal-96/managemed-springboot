package com.managemed.managemedapp.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managemed.managemedapp.dao.DAOImpl;
import com.managemed.managemedapp.model.Cart;
import com.managemed.managemedapp.model.Order;
import com.managemed.managemedapp.model.Payment;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class StripeCheckoutService {

        @Autowired
        AddCartService addCartService;
        @Autowired
        OrderService orderService;
        @Autowired
        PaymentService paymentService;

    public String createCheckoutSession(
            String username,
            double shippingPrice,
            String domainUrl) throws SQLException, StripeException {

        // DAOImpl dao = DAOImpl.getInstance();
        // LocalDate currentDate = LocalDate.now();

        List<Cart> reservedItems = addCartService.reserveCart(username);
        
        // ResultSet rs = dao.viewcart(username);

        if (reservedItems != null) {
            int orderQty = addCartService.getCartCount(username);
            Order order = orderService.createPendingOrder(username, orderQty);
            paymentService.createOrUpdatePendingPayment(order.getId());
        //     dao.proceedPayment(currentDate, username);
        }

        SessionCreateParams.Builder paramsBuilder =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(domainUrl + "/success")
                        .setCancelUrl(domainUrl + "/cancel");

        for (Cart cart : reservedItems) {
            String productName = cart.getProduct().getProduct();
            int quantity = cart.getQuantity();
            long totalPrice = cart.getPrice();
            long unitPrice = totalPrice / quantity;

            paramsBuilder.addLineItem(
                    SessionCreateParams.LineItem.builder()
                            .setQuantity((long) quantity)
                            .setPriceData(
                                    SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency("usd")
                                            .setUnitAmount(unitPrice * 100)
                                            .setProductData(
                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                            .setName(productName)
                                                            .build())
                                            .build())
                            .build());
        }

        paramsBuilder.addLineItem(
                SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("usd")
                                        .setUnitAmount((long) shippingPrice * 100)
                                        .setProductData(
                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                        .setName("Shipping Price")
                                                        .build())
                                        .build())
                        .build());

        Session session = Session.create(paramsBuilder.build());
        return session.getUrl();
    }
}
