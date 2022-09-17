create database auditDB
use auditDB;


use auditDB
CREATE TABLE action_type
(
    id integer primary key auto_increment,
    code  	 varchar(30) not null,
    name_en  varchar(30)  NOT NULL,
    name_ar  varchar(30) NOT NULL,
    message_template_en varchar(100) NOT NULL,
    message_template_ar varchar(100) NOT NULL
);


insert into action_type values(1,'order_created','order created','تم إنشاء الطلب',
                               'Customer {{customer.value}} created order {{order.value}} to buy product {{product.value}}',
                               'قام العميل {{customer.name}} بإنشاء الطلب {{order.value}} لشراء منتج {{product.value}}');

insert into action_type values(2,'order_refunded','order refunded','تم إلغاء الطلب',
                               'User {{user.value}} refunded order {{order.value}} created by customer: {{customer.value}}',
                               'قام المستخدم {{user.value}} بإلغاء الطلب {{order.value}} الذي تم إنشاؤه بواسطة العميل {{customer.value}}');


create table user
(
    id integer primary key auto_increment,
    value varchar(15) not null
);
insert into user values(1,'ahmed');

create table be(
                   id integer primary key auto_increment,
                   value varchar(15) not null
);
insert into be values(1,'vodafone');

create table application(
                            id integer primary key auto_increment,
                            price double
);
insert into application values(1,20);

create table action (
                        id integer primary key auto_increment,
                        description_en varchar(100) not null,
                        description_ar varchar(100) not null,
                        time date not null,
                        application_id integer not null,
                        user_id integer not null,
                        be_id integer not null,
                        action_type_id integer not null,
                        FOREIGN KEY (action_type_id) REFERENCES action_type(id),
                        FOREIGN KEY (application_id) REFERENCES application(id),
                        FOREIGN KEY (user_id) REFERENCES user(id),
                        FOREIGN KEY (be_id) REFERENCES be(id)
);

drop table param;

insert into action values(1,'test','test','2012-04-23T18:25:43.511Z',1,1,1,2);

create table param_type
(
    id integer primary key auto_increment,
    code  	 varchar(15) not null,
    name_en  varchar(15)  not null,
    name_ar  varchar(15) not null
);

insert into param_type values(1,'customer','Customer','عميل');
insert into param_type values(2,'order','Order','طلب');
insert into param_type values(3,'product','Product','منتج');

create table param(
                      id integer primary key auto_increment,
                      identifier varchar(30) not null,
                      value  varchar(30) not null,
                      param_type_id integer not null,
                      action_id integer not null,
                      FOREIGN KEY (param_type_id) REFERENCES param_type(id),
                      FOREIGN KEY (action_id) REFERENCES action(id)
);

