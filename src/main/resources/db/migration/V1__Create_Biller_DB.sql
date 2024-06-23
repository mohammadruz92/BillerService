CREATE TABLE `transaction` (
  `created_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL,
  `guid` varchar(255) DEFAULT NULL,
  `request` json DEFAULT NULL,
  `response` json DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `transaction_seq` (
  `next_val` bigint DEFAULT NULL
);

CREATE TABLE `payment_notification` (
  `fees_on_biller` bit(1) DEFAULT NULL,
  `rcv_code` int DEFAULT NULL,
  `sdr_code` int DEFAULT NULL,
  `id` bigint NOT NULL,
  `access_channel` varchar(255) DEFAULT NULL,
  `bank_code` varchar(255) DEFAULT NULL,
  `bank_trx_id` varchar(255) DEFAULT NULL,
  `bill_no` varchar(255) DEFAULT NULL,
  `billing_no` varchar(255) DEFAULT NULL,
  `due_amt` varchar(255) DEFAULT NULL,
  `fees_amt` varchar(255) DEFAULT NULL,
  `guid` varchar(255) DEFAULT NULL,
  `joe_bpps_trx` varchar(255) DEFAULT NULL,
  `paid_amt` varchar(255) DEFAULT NULL,
  `payer_id` varchar(255) DEFAULT NULL,
  `payer_id_type` varchar(255) DEFAULT NULL,
  `payer_nation` varchar(255) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `payment_type` varchar(255) DEFAULT NULL,
  `pmt_status` varchar(255) DEFAULT NULL,
  `prepaid_cat` varchar(255) DEFAULT NULL,
  `process_date` varchar(255) DEFAULT NULL,
  `req_type` varchar(255) DEFAULT NULL,
  `service_type` varchar(255) DEFAULT NULL,
  `stmt_date` varchar(255) DEFAULT NULL,
  `timestamp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `payment_notification_seq` (
  `next_val` bigint DEFAULT NULL
);

CREATE TABLE `sub_pmts` (
  `set_bnk_code` int DEFAULT NULL,
  `payment_notification_id` bigint NOT NULL,
  `acct_no` varchar(255) DEFAULT NULL,
  `amount` varchar(255) DEFAULT NULL,
  KEY `FKeg9j4wyyyp75k7pu8kxaggq75` (`payment_notification_id`),
  CONSTRAINT `FKeg9j4wyyyp75k7pu8kxaggq75` FOREIGN KEY (`payment_notification_id`) REFERENCES `payment_notification` (`id`)
);

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(120) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
);

CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` enum('ROLE_ADMIN','ROLE_MODERATOR','ROLE_USER') DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user_roles` (
  `role_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FKhfh9dx7w3ubf1co1vdev94g3f` (`user_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);






