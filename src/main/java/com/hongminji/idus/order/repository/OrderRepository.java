package com.hongminji.idus.order.repository;

import com.hongminji.idus.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, String> {

    Optional<Order> findById(String orderNum);

    @Query("SELECT o FROM orders o INNER JOIN Member m ON o.member = m WHERE m.email = :email")
    List<Order> findOrderByMemberEmail(String email);

}
