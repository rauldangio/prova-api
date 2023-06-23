create table contasreceber(
    id int not null auto_increment primary key,
    data_conta date not null,
    idcliente int not null,
    valor_conta decimal(12,2) not null
);