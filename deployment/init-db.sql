-- -- ### Создание роли базы данных ###
CREATE ROLE ads_role WITH LOGIN PASSWORD 'ads_q1';

-- -- ### Создание базы данных ###
CREATE DATABASE ads OWNER ads_role;
