package com.managemed.managemedapp.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.managemed.managemedapp.dao.DAOImpl;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class StripeCheckoutService {

    public String createCheckoutSession(
            String username,
            int shippingPrice,
            String domainUrl) throws SQLException, StripeException {

        DAOImpl dao = DAOImpl.getInstance();
        LocalDate currentDate = LocalDate.now();

        dao.reserveCart(username);
        ResultSet rs = dao.viewcart(username);

        if (rs != null) {
            int orderQty = dao.getCartCount(username);
            dao.createOrder(username, orderQty, "PENDING", currentDate);
            dao.proceedPayment(currentDate, username);
        }

        SessionCreateParams.Builder paramsBuilder =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(domainUrl + "/success")
                        .setCancelUrl(domainUrl + "/cancel");

        while (rs.next()) {
            String productName = rs.getString(2);
            int quantity = rs.getInt(3);
            long totalPrice = rs.getInt(4);
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
