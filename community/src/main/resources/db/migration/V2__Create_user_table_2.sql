CREATE TABLE `user` (
        `id` int(11) NOT NULL AUTO_INCREMENT,
        `name` varchar(50) DEFAULT NULL,
        `account_id` varchar(100) DEFAULT NULL,
        `token` varchar(36) DEFAULT NULL,
        `gmt_create` bigint(20) DEFAULT NULL,
        `gmt_modified` bigint(20) DEFAULT NULL,
        PRIMARY KEY (`id`)
)