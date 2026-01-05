package com.managemed.managemedapp.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managemed.managemedapp.model.*;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    
    Optional<Cart> findByUserUsernameAndProductProductAndOrderIdIsNull(
            String username, String product);

    long countByUserUsernameAndOrderIdIsNull(String username);

    void deleteByUserUsernameAndProductProduct(String username, String product);

    List<Cart> findByUserUsernameAndOrderIdIsNull(String username);
}
