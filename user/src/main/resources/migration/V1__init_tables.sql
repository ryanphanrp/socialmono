create table if not exists users
(
    user_id     bigint primary key unique not null,
    username    nvarchar(56) unique       not null,
    email       nvarchar(255) unique,
    password    nvarchar(56),
    status      ENUM ('ACTIVE', 'NOT_VERIFIED', 'DEACTIVATED') default 'NOT_VERIFIED',
    avatar_url  nvarchar(255),
    dob         datetime,
    gender      enum ('MALE', 'FEMALE'),
    date_joined datetime default now()
);