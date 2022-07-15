package com.hongminji.idus.order.OrderService;

import com.hongminji.idus.order.entity.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderServiceTest {

    @Nested
    class Success {

        @Test
        @DisplayName("주문 성공")
        public void 주문번호_성공() {
            Order order = Order.builder()
                    .orderNum("LOVEIDUS1234")
                    .productName("emoji 포함 상품 ❤️")
                    .build();

            assertThat(order.getOrderNum().length()).isEqualTo(12);
        }
    }

}
