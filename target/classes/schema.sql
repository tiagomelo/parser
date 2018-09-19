create table `log` (
	`id` int not null auto_increment,
	`access_date` datetime not null,
	`ip_address` varchar(45) not null,
	`access_url` varchar(100) not null,
	`http_status` tinyint(8) unsigned not null,
	`access_user_agent` varchar(100) not null,
	primary key (`id`),
	key (`ip_address`)
) engine=InnoDB default charset=utf8;

create table `log_audit` (
	`id` int not null auto_increment,
	`ip_address` varchar(45) not null,
	`threshold` integer unsigned not null,
	`block_reason` varchar(400) not null,
	primary key (`id`)
) engine=InnoDB default charset=utf8;