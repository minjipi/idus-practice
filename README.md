📙 홍민지 Idus homework 
================
<hr />

+ Swagger URl : {domain}/swagger-ui/index.html#/ <br /> <br />

+ 테이블 생성 쿼리문:
  
CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_general_ci NOT NULL,
  `nickname` varchar(30) COLLATE utf8_general_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_general_ci NOT NULL,
  `phone_num` varchar(20) COLLATE utf8_general_ci NOT NULL UNIQUE,
  `email` varchar(100) COLLATE utf8_general_ci NOT NULL UNIQUE,
  `gender` varchar(1) COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `orders` (
`order_num` varchar(12) COLLATE utf8_general_ci NOT NULL,
`created_at` datetime(6) DEFAULT NULL,
`product_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
`member_id` bigint(20) DEFAULT NULL,
PRIMARY KEY (`order_num`),
CONSTRAINT orders_member
FOREIGN KEY (`member_id`)
REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `denied_token` (
`access_token` varchar(255) COLLATE utf8_general_ci NOT NULL,
`email` varchar(255) COLLATE utf8_general_ci DEFAULT NULL,
PRIMARY KEY (`access_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `refresh_token` (
`refresh_token_key` varchar(255) COLLATE utf8_general_ci NOT NULL,
`refresh_token_value` varchar(255) COLLATE utf8_general_ci DEFAULT NULL,
PRIMARY KEY (`refresh_token_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


