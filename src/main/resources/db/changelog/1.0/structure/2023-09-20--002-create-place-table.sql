create table if not exists branches.place (
    id serial PRIMARY KEY,
    place_type varchar(255),
    address VARCHAR(255),
    city VARCHAR(255),
    coordinates geography(POINT),

    -- branch fields
    title VARCHAR(255),
    service_natural_entity boolean,
    service_legal_entity boolean,
    service_low_mobility boolean,
    service_premium boolean,
    services jsonb,
    natural_entity_schedule jsonb,
    legal_entity_schedule jsonb,
    text_schedule jsonb,
    line_time integer,

    -- atm fields
    owner_bank VARCHAR(255),
    organization VARCHAR(255),
    working_type VARCHAR(255),
    schedule jsonb
);