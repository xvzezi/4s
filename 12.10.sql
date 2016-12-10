create table storefront(
	storefront_id int,
    storefront_name varchar(32),
    storefront_password varchar(64),
    storefront_description varchar(256),
    primary key (storefront_id)
);

/**
1. 创建storefront
2. 在相关的实体中加入storefront_id
	vehicle
	user
	money_flow 没有对应的bean实体
	sales_plan
	market_activity
	insurance
	gift
	customer
	additional_produ
 */