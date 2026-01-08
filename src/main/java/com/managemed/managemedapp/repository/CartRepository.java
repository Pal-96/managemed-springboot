package com.managemed.managemedapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.managemed.managemedapp.dto.OrderItemDTO;
import com.managemed.managemedapp.model.*;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

        Optional<Cart> findByUserUsernameAndProductProductAndOrderIdIsNull(
                        String username, String product);

        long countByUserUsernameAndOrderIdIsNull(String username);

        void deleteByUserUsernameAndProductProduct(String username, String product);

        List<Cart> findByUserUsernameAndOrderIdIsNull(String username);

        List<Cart> findByUserUsernameAndCartStatus(String username, String cartStatus);

        List<Cart> findByUserAndCartStatus(User user, String cartStatus);

        long countByUserAndOrderIdIsNull(User user);

        @Modifying
        @Transactional
        @Query("""
                            UPDATE Cart c
                            SET c.cartStatus = :newStatus,
                            c.orderId = :orderId
                            WHERE c.user.username = :username
                              AND c.product.product = :product
                              AND c.cartStatus IN :statuses
                        """)
        void clearCartStatus(
                        @Param("username") String username,
                        @Param("product") String product,
                        @Param("newStatus") String newStatus,
                        @Param("orderId") Long orderId,
                        @Param("statuses") List<String> statuses);

        @Query("""
                            SELECT new com.managemed.managemedapp.dto.OrderItemDTO(
                                c.product.product,
                                c.quantity,
                                c.price,
                                o.orderDate
                            )
                            FROM Cart c
                            JOIN Order o ON c.orderId = o.id
                            WHERE c.user.username = :username
                              AND c.cartStatus = 'PURCHASED'
                        """)
        List<OrderItemDTO> findPurchasedOrders(@Param("username") String username);

}
