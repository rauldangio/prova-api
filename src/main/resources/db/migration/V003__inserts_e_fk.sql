 insert into cliente(nomecliente) values("gabriel");
 insert into cliente(nomecliente) values("raul");
insert into cliente(nomecliente) values("Gi");
insert into cliente(nomecliente) values("Vitao");

alter table contasreceber add constraint fk_contasreceber_cliente foreign key(idcliente) references contasreceber(id);

insert into contasreceber(dataconta, idcliente, valorconta) values('2023-03-22', 1, 12311.23);
insert into contasreceber(dataconta, idcliente, valorconta) values('2023-04-22', 2, 1231311.23);
insert into contasreceber(dataconta, idcliente, valorconta) values('2023-05-22', 3, 122311.23);
insert into contasreceber(dataconta, idcliente, valorconta) values('2023-06-22', 4, 1231421.23);

