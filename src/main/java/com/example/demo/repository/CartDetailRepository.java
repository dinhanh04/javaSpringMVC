package com.example.demo.repository;

import com.example.demo.domain.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    List<CartDetail> findByCartId(Long cartId);

    @Query("SELECT cd FROM CartDetail cd WHERE cd.cartId = :cartId AND cd.productId = :productId")
    CartDetail findByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);
}
