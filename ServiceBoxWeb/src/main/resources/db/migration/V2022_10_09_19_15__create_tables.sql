create table address
(
    id           bigint auto_increment
        primary key,
    city         varchar(255) not null,
    flat_number  varchar(255) null,
    house_number varchar(255) not null,
    street       varchar(255) not null,
    zip_code     varchar(6)   not null
);

create table repair_card
(
    id bigint auto_increment
        primary key
);

create table part
(
    id             bigint auto_increment
        primary key,
    cost           double       not null,
    name           varchar(255) not null,
    quantity       int          not null,
    repair_card_id bigint       null,
    constraint FKa0wlvfnwpk3w3tgkhgxh3n901
        foreign key (repair_card_id) references repair_card (id)
);

create table repair
(
    id             bigint auto_increment
        primary key,
    cost           double       not null,
    description    varchar(255) not null,
    repair_card_id bigint       null,
    constraint FKru8g97aan5kjcofsrt0i5y5tx
        foreign key (repair_card_id) references repair_card (id)
);

create table role
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

create table user
(
    id               bigint auto_increment
        primary key,
    enabled          tinyint(1) default 1 null,
    initial_password tinyint(1) default 1 null,
    password         varchar(255)         not null,
    username         varchar(255)         not null,
    constraint UK_sb8bbouer5wak8vyiiy4pf2bx
        unique (username)
);

create table client
(
    id                  bigint auto_increment
        primary key,
    allow_notifications tinyint(1) default 0 null,
    email               varchar(255)         not null,
    name                varchar(255)         not null,
    nip                 varchar(10)          null,
    phone               varchar(255)         not null,
    address_id          bigint               null,
    user_id             bigint               null,
    constraint UK_bfgjs3fem0hmjhvih80158x29
        unique (email),
    constraint UK_d7c4jgrjortusykiwq2728d2g
        unique (address_id),
    constraint FKb137u2cl2ec0otae32lk5pcl2
        foreign key (address_id) references address (id),
    constraint FKk1fi84oi1yyuswr40h38kjy1s
        foreign key (user_id) references user (id)
);

create table user_role
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FK859n2jvi8ivhui0rl0esws6o
        foreign key (user_id) references user (id),
    constraint FKa68196081fvovjhkek5m97n3y
        foreign key (role_id) references role (id)
);

create table vehicle
(
    id              bigint auto_increment
        primary key,
    engine_capacity double       null,
    make            varchar(255) not null,
    mileage         int          null,
    model           varchar(255) not null,
    plate_number    varchar(255) not null,
    production_year int          null,
    vin             varchar(17)  null,
    client_id       bigint       null,
    constraint UK_avfc6x9pcl38sop7lqocxppbb
        unique (plate_number),
    constraint FKn1vrl9mv7poohmt9xys1ot5p3
        foreign key (client_id) references client (id)
);

create table service_order
(
    id             bigint auto_increment
        primary key,
    created_at     datetime   default CURRENT_TIMESTAMP null,
    description    varchar(255)                         not null,
    finished_at    datetime(6)                          null,
    max_cost       int                                  not null,
    new_parts      tinyint(1) default 0                 null,
    order_number   varchar(255)                         not null,
    state          varchar(255)                         not null,
    repair_card_id bigint                               null,
    vehicle_id     bigint                               null,
    constraint UK_el6eqkd4xk2sn78cjk0mjgd8e
        unique (order_number),
    constraint UK_k08tqsh9sil99x3lgvafmrgs3
        unique (repair_card_id),
    constraint FK17blbqnk4vtr313dud034ywe4
        foreign key (repair_card_id) references repair_card (id),
    constraint FKh86xc20x2w0q4jygrkqr0xt7q
        foreign key (vehicle_id) references vehicle (id)
);

create table note
(
    id               bigint auto_increment
        primary key,
    created_at       datetime default CURRENT_TIMESTAMP null,
    note             varchar(255)                       not null,
    service_order_id bigint                             null,
    constraint FK31ol80qxxlexa4i5t6fcoyj41
        foreign key (service_order_id) references service_order (id)
);
