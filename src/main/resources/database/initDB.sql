--основная сущность, на которую все завязано
CREATE TABLE IF NOT EXISTS PERSON
(
    id          INT GENERATED ALWAYS AS IDENTITY,
    first_name  VARCHAR(100),
    middle_name VARCHAR(100),
    last_name   VARCHAR(100),
    birth_date  DATE,

    PRIMARY KEY (id)
);

--IdentityDocument - [OneToMany] разные документы, удостоверяющие личность
CREATE TABLE IF NOT EXISTS IDENTITY_DOCUMENT
(
    id        INT GENERATED ALWAYS AS IDENTITY,
    type      VARCHAR(30),
    number    VARCHAR(30), -- todo добавить констрейнтов на длину для разных типов
    main      BOOLEAN,     -- todo констрейнт чтобы только 1 был
    person_id INT NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_person
        FOREIGN KEY (person_id)
            REFERENCES PERSON (id)
    -- todo добавить уникальный составной индекс на тип+номер
);

--Регионы
CREATE TABLE IF NOT EXISTS REGION
(
    id   INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(30),

    PRIMARY KEY (id)
);

--Address - [ManyToMany]
CREATE TABLE IF NOT EXISTS ADDRESS
(
    id        INT GENERATED ALWAYS AS IDENTITY,
    value     VARCHAR(200),
    region_id INT NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_region
        FOREIGN KEY (region_id)
            REFERENCES REGION (id)
);

-- Таблица для ManyToMany связи
CREATE TABLE IF NOT EXISTS PERSON_ADDRESS
(
    person_id    INT NOT NULL,
    address_id   INT NOT NULL,
    registration BOOLEAN,

    PRIMARY KEY (person_id, address_id)
);

--Contact - контакты [OneToMany]
CREATE TABLE IF NOT EXISTS CONTACT
(
    id        INT GENERATED ALWAYS AS IDENTITY,
    type      VARCHAR(20),
    value     VARCHAR(30),
    person_id INT NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_person
        FOREIGN KEY (person_id)
            REFERENCES PERSON (id)
);