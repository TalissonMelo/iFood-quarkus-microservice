INSERT INTO localizacao (id, latitude, longitude) VALUES(1000, -15.817759, -47.836959);

INSERT INTO restaurante (id, localizacao_id, nome) VALUES(999, 1000, 'Manguai');

INSERT INTO prato (id, nome, descricao, restaurante_id, preco)
VALUES(9998, 'Cuscuz com Ovo', 'Bom demais no café da manhã', 999, 3.99);

INSERT INTO prato (id, nome, descricao, restaurante_id, preco)
VALUES(9997, 'Peixe frito', 'Agulhinha frita, excelente com Cerveja', 999, 99.99);