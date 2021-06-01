truncate table usuario restart identity cascade;
truncate table perfil restart identity cascade;
truncate table feedback restart identity cascade;
truncate table agendamento_vacina restart identity cascade;
truncate table carteira_vacinacao restart identity cascade;

insert into usuario(nome, cpf, email, senha, telefone, is_ativo, cep, endereco, complemento, bairro, numero, cidade, estado) values
('Mark zuckberg', '16010903076', 'mark@gmail.com', '$2a$10$ygyHtHjjY9lJgpAzG/4HSuZORj9x/H/B2jNYDHRLnnxfH4n.Sl.xW', '55992113435', 'TRUE', '01001000', 'Praça da Sé', 'lado ímpar', 'Sé', '330', 'São Paulo', 'São Paulo'),
('Bill gates', '79180705006', 'bill@gmail.com', '$2a$10$ygyHtHjjY9lJgpAzG/4HSuZORj9x/H/B2jNYDHRLnnxfH4n.Sl.xW', '55992113435', 'TRUE', '01001000', 'Praça da Sé', 'lado ímpar', 'Sé', '330', 'São Paulo', 'São Paulo');

insert into perfil(usuario_id, perfis) values
(1, 'USUARIO'), (1, 'ADMIN'),
(2, 'USUARIO');

insert into feedback(comentario, data, usuario_id) values 
('peço para que consertem urgentemente', '2021-05-08', 1),
('Adorei a proposta do app, muito bom!', '2021-05-07', 1),
('Há um bug na tela de carregamento', '2021-05-07', 2);

insert into agendamento_vacina (data_agendamento, is_vacina_tomada, tipo_vacina, usuario_id) values
('2021-05-09 03:00:00', 'FALSE', 'Covid', 1),
('2021-04-04', 'TRUE', 'Covid', 2);