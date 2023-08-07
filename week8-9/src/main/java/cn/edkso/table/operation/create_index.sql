create table t_message(
    id int unsigned primary key ,
    content varchar(200) not null ,
    type enum('公告','通报','个人通知') not null ,
    create_time timestamp not null ,
    index idx_type (type)
);

# 删除索引
drop index idx_type on t_message;

# 增加索引(两种方式)
create index idx_type on t_message(type);
alter table t_message add index idx_type (id);

# 查看索引
show index from t_message;

