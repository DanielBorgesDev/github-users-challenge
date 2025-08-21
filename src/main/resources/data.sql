-- Inserindo usuários
INSERT INTO users (login, url) VALUES
  ('danielborges', 'https://github.com/danielborges'),
  ('maria', 'https://github.com/maria'),
  ('joao', 'https://github.com/joao');

-- Inserindo perfis/roles
INSERT INTO roles (name) VALUES
  ('ADMIN'),
  ('USER'),
  ('DEV');

-- Ligando usuários às roles
INSERT INTO user_roles (user_id, role_id) VALUES
  (1, 1), -- danielborges -> ADMIN
  (1, 3), -- danielborges -> DEV
  (2, 2), -- maria -> USER
  (3, 2), -- joao -> USER
  (3, 3); -- joao -> DEV
