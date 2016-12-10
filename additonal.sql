create table market_activity(
  id int,
  theme varchar(64),
  target varchar(64),
  start date,
  end date,
  fee int,
  in_onSale varchar(64),
  out_onSale varchar(64),
  carType varchar(64),
  plan_invite int,
  plan_nature int,
  plan_trans int,
  fact_invite int,
  fact_nature int,
  fact_trans int,
  con_opt varchar(255),
  con_neg varchar(255),
  primary key(id)
);

create table bean(
  bean_id int,
  user_id int,
  brand varchar(64),
  sale_date date,
  opt_whole int,
  opt_substitute int,
  opt_gift int,
  opt_insur int,
  opt_fin int,
  opt_serve int,
  opt_delay_insur int,
  opt_vip int,
  opt_lease int,
  neg_cr int,
  neg_man int,
  sale_achieve int,
  profit_achieve int,
  primary key(bean_id)
);

create table sole_service_type(
  sole_service_type_id  int,
  sole_service_description  varchar(64),
  primary key(sole_service_type_id)
)

create table sole_service(
  sole_service_id int,
  car_id varchar(32),
  car_type varchar(32),
  car_class varchar(32),
  lease_start date,
  lease_end date,
  rebate  int,
  profit  int,
  primary key(sole_service_id)
)

create table sst_content(
  sole_service_id int REFERENCES sole_service(sole_service_id),
  sole_service_type_id int REFERENCES sole_service_type(sole_service_type_id),
  income  int,
  raw_profit  int,
  PRIMARY KEY (sole_service_id, sole_service_type_id)
)