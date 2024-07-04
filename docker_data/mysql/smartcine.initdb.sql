create table
  cinema (
    id binary(16) not null primary key,
    variant varchar(50) not null,
    name varchar(50) not null
  );

create table
  cinema_layout (
    id binary(16) not null primary key,
    type enum ('RECTANGLE', 'DYNAMIC') null,
    data varchar(50) not null
  );

create table
  cinema_room (
    id binary(16) not null primary key,
    cinema_id binary(16) not null,
    cinema_layout_id binary(16) not null,
    name varchar(255) null,
    type enum ('NORMAL', 'VIP', 'DELUXE') null,
    constraint cinema_room_ibfk_1 foreign key (cinema_id) references cinema (id),
    constraint cinema_room_ibfk_2 foreign key (cinema_layout_id) references cinema_layout (id)
  );

create index cinema_id on cinema_room (cinema_id);

create index cinema_layout_id on cinema_room (cinema_layout_id);

create table
  film (
    id binary(16) not null primary key,
    title varchar(255) not null,
    director varchar(50) null,
    release_date timestamp null,
    country varchar(50) null,
    restrict_age int null,
    duration int null,
    picture_url varchar(255) null,
    trailer_url varchar(255) not null,
    language varchar(50) not null,
    description varchar(255) null
  );

create table
  item (
    id binary(16) not null primary key,
    item_name varchar(255) not null,
    amount double not null,
    discount double not null,
    status varchar(255) null
  );

create table
  m2m_cinema_layout (
    cinema_id binary(16) not null,
    cinema_layout_id binary(16) not null,
    constraint FKafapjkmt7fxwfu6sd839wv65t foreign key (cinema_id) references cinema (id),
    constraint FKg80c3r1r3crpitew2ckvtajt foreign key (cinema_layout_id) references cinema_layout (id)
  );

create table
  perform (
    id binary(16) not null primary key,
    film_id binary(16) not null,
    dest_id binary(16) not null,
    start_time timestamp not null,
    end_time timestamp not null,
    translate_type enum ('EngSub', 'VieSub') null,
    view_type enum ('_2D', '_3D', '_4D') null,
    price double not null,
    cinema_room_id binary(16) null,
    constraint FKeu7wyv7o56777oo642mwij238 foreign key (cinema_room_id) references cinema_room (id),
    constraint perform_ibfk_1 foreign key (film_id) references film (id),
    constraint perform_ibfk_4 foreign key (dest_id) references cinema_room (id)
  );

create table
  payment (
    id binary(16) not null primary key,
    user_id binary(16) not null,
    date_created timestamp not null,
    date_expired timestamp not null,
    status varchar(50) not null,
    item_id binary(16) not null,
    perform_id binary(16) not null,
    date_create datetime (6) null,
    date_expire datetime (6) null,
    constraint payment_ibfk_3 foreign key (item_id) references item (id),
    constraint payment_ibfk_4 foreign key (perform_id) references perform (id)
  );

create table
  item_payments (
    item_id binary(16) not null,
    payments_id binary(16) not null,
    constraint UK_anw8yba21b9lbv92gdbuap1v3 unique (payments_id),
    constraint FKlbtxyk9mdohidwx6ogmgtvqb5 foreign key (item_id) references item (id),
    constraint FKqvdmu6jgcm8khkodg4c4a275m foreign key (payments_id) references payment (id)
  );

create index item_id on payment (item_id);

create index perform_id on payment (perform_id);

create index user_id on payment (user_id);

create index dest_id on perform (dest_id);

create index film_id on perform (film_id);

create table
  seat_payment (
    id binary(16) not null primary key,
    pick_seat_id binary(16) null,
    payment_id binary(16) null,
    constraint FKt7a8nyoomn5js0uye7a4s3ste foreign key (payment_id) references payment (id)
  );

create table
  seat_price (
    id binary(16) not null primary key,
    price bigint null,
    x int null,
    y int null,
    perform_id binary(16) null,
    constraint FKal69sspaj8nmlaq5288o63klr foreign key (perform_id) references perform (id)
  );

create table
  tag (
    id binary(16) not null primary key,
    name varchar(255) null
  );

create table
  m2m_film_tag (
    film_id binary(16) not null,
    tag_id binary(16) not null,
    constraint FKeenhf0isewcem0g3k3p4gc4b1 foreign key (film_id) references film (id),
    constraint FKjxxi3qbe4vliyixnksmx5151k foreign key (tag_id) references tag (id)
  );

create table
  users (
    id binary(16) not null primary key,
    email varchar(50) not null,
    password varchar(100) not null,
    role enum ('USER', 'MANAGER_ADMIN', 'OWNER') null,
    name varchar(50) not null,
    payment_id binary(16) null,
    first_name varchar(255) null,
    last_name varchar(255) null,
    constraint users_ibfk_1 foreign key (payment_id) references payment (id)
  );

create table
  cinema_manager (
    cinema_id binary(16) not null,
    user_id binary(16) not null,
    primary key (cinema_id, user_id),
    constraint cinema_manager_ibfk_1 foreign key (cinema_id) references cinema (id),
    constraint cinema_manager_ibfk_2 foreign key (user_id) references users (id)
  );

create index user_id on cinema_manager (user_id);

create table
  comment (
    id binary(16) not null primary key,
    user_id binary(16) not null,
    body varchar(250) not null,
    film_id binary(16) null,
    type enum ('FILM', 'USER', 'COMMENT') null,
    constraint FKb6gnv47yxa2jewd4jpvm3pnfk foreign key (film_id) references film (id),
    constraint comment_ibfk_1 foreign key (user_id) references users (id)
  );

create index user_id on comment (user_id);

alter table payment add constraint payment_ibfk_1 foreign key (user_id) references users (id);

create table
  pick_seat (
    id binary(16) not null primary key,
    perform_id binary(16) null,
    user_id binary(16) null,
    code varchar(255) null,
    constraint FKcdfhjbqb1ypqiu9kdaitfok1s foreign key (perform_id) references perform (id),
    constraint FKde2kvigd2bvo711m053w73i65 foreign key (user_id) references users (id)
  );

create index payment_id on users (payment_id);