CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE items (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    quantidade INTEGER NOT NULL CHECK (quantidade >= 0),
    valor DECIMAL(10,2) NOT NULL CHECK (valor >= 0),
    imagem VARCHAR(255) NOT NULL
);

CREATE TABLE movimentacoes (
    id SERIAL PRIMARY KEY,
    quantidade INTEGER NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    item_id INTEGER NOT NULL,
    data DATE NOT NULL,
    CONSTRAINT fk_item FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
);