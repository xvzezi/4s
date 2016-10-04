create table market_activity(
	id int,
	theme varchar(64),
    target varchar(64),
    start date,
    end date,
    fee date,
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

