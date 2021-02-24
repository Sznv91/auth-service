
-- ACCESS TOKEN VALIDITY = 300 SECOND
-- REFRESH TOKEN VALIDITY = 1800 SECOND
-- insert client details [clientId = atom & clientSecret = Secret_password123]
INSERT INTO oauth_client_details
(client_id, client_secret, scope, authorized_grant_types,
 authorities, access_token_validity, refresh_token_validity)
VALUES ('atom', '$2a$10$C0EFKVovofPN0KmaJsJN4.jqQjjAWzkOlz1UVAeoDq6HiQceNEmFK',
        'read,write', 'password,refresh_token,client_credentials',
        'ROLE_TRUSTED_CLIENT', 300, 1800);
