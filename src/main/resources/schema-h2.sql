drop table if exists oauth_access_token;
create table oauth_access_token(
  token_id          TEXT,
  token             varbinary(MAX),
  authentication_id TEXT,
  user_name         TEXT,
  client_id         TEXT,
  authentication    varbinary(MAX),
  refresh_token     TEXT
);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token(
  token_id        TEXT,
  token           varbinary(MAX),
  authentication  varbinary(MAX)
);


--CLIENT CREDENTIAL TABLES--

drop table if exists oauth_client_details;
create table oauth_client_details(
  client_id               VARCHAR PRIMARY KEY,
  resource_ids            TEXT,
  client_secret           TEXT,
  scope                   TEXT,
  authorized_grant_types  TEXT,
  web_server_redirect_uri TEXT,
  authorities             TEXT,
  access_token_validity   INTEGER,
  refresh_token_validity  INTEGER,
  additional_information  TEXT,
  autoapprove             TEXT
);

/*-- ACCESS TOKEN VALIDITY = 300 SECOND
-- REFRESH TOKEN VALIDITY = 1800 SECOND
-- insert client details [clientId = atom & clientSecret = Secret_password123]
INSERT INTO oauth_client_details
(client_id, client_secret, scope, authorized_grant_types,
 authorities, access_token_validity, refresh_token_validity)
VALUES ('atom', '$2a$10$C0EFKVovofPN0KmaJsJN4.jqQjjAWzkOlz1UVAeoDq6HiQceNEmFK',
        'read,write', 'password,refresh_token,client_credentials',
        'ROLE_TRUSTED_CLIENT', 300, 1800);*/