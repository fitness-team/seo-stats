CREATE TABLE sp_site (
    id serial PRIMARY KEY,
    name varchar(100) DEFAULT NULL,
    domain varchar(100) NOT NULL,
    top integer DEFAULT NULL,
    start_date timestamp DEFAULT current_timestamp,
    end_date timestamp DEFAULT NULL,
    enable boolean DEFAULT false
);

CREATE TABLE sp_word (
    id serial PRIMARY KEY,
    site_id integer NOT NULL,
    word varchar(1000) NOT NULL,
    start_date timestamp DEFAULT current_timestamp,
    end_date timestamp DEFAULT NULL,
    enable boolean DEFAULT false
);

CREATE TABLE sp_position_statistic (
    id serial PRIMARY KEY,
    word_id integer NOT NULL,
    year integer NOT NULL,
    month integer NOT NULL,
    d1 integer DEFAULT NULL,
    d2 integer DEFAULT NULL,
    d3 integer DEFAULT NULL,
    d4 integer DEFAULT NULL,
    d5 integer DEFAULT NULL,
    d6 integer DEFAULT NULL,
    d7 integer DEFAULT NULL,
    d8 integer DEFAULT NULL,
    d9 integer DEFAULT NULL,
    d10 integer DEFAULT NULL,
    d11 integer DEFAULT NULL,
    d12 integer DEFAULT NULL,
    d13 integer DEFAULT NULL,
    d14 integer DEFAULT NULL,
    d15 integer DEFAULT NULL,
    d16 integer DEFAULT NULL,
    d17 integer DEFAULT NULL,
    d18 integer DEFAULT NULL,
    d19 integer DEFAULT NULL,
    d20 integer DEFAULT NULL,
    d21 integer DEFAULT NULL,
    d22 integer DEFAULT NULL,
    d23 integer DEFAULT NULL,
    d24 integer DEFAULT NULL,
    d25 integer DEFAULT NULL,
    d26 integer DEFAULT NULL,
    d27 integer DEFAULT NULL,
    d28 integer DEFAULT NULL,
    d29 integer DEFAULT NULL,
    d30 integer DEFAULT NULL,
    d31 integer DEFAULT NULL,
    last_update timestamp DEFAULT current_timestamp
);

CREATE TABLE sp_position_check (
    id serial PRIMARY KEY,
    word_id integer NOT NULL,
    position integer DEFAULT NULL,
    time_check timestamp DEFAULT current_timestamp,
    date_check timestamp DEFAULT NULL
);


CREATE TABLE sp_site_search_filter (
    id serial PRIMARY KEY,
    site_id integer NOT NULL,
    filter_id integer NOT NULL,
    value varchar(100) NOT NULL
);

CREATE TABLE sp_search_filter (
    id serial PRIMARY KEY,
    search_engine varchar(10) NOT NULL,
    name varchar(50) NOT NULL,
    ui_name varchar(100) NOT NULL
);