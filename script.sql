drop table if exists xhs_supplier_info;

/*==============================================================*/
/* Table: xhs_supplier_info                                     */
/*==============================================================*/
create table xhs_supplier_info
(
   id                   varchar(50) not null comment '主键',
   name                 varchar(100) comment '供应商名称',
   addr                 varchar(100) comment '供应商地址',
   phone_number         varchar(20) comment '办公电话',
   mobile_number        varchar(20) comment '移动电话',
   email                varchar(50) comment '电子邮箱',
   qq_number            varchar(20) comment '企业qq号',
   webchat_number       varchar(20) comment '企业微信号',
   remarks              varchar(500) comment '备注',
   sort                 int comment '权重',
   status               int comment '状态（0-有效；1-删除）',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id)
);

alter table xhs_supplier_info comment '供应商信息';


drop table if exists xhs_supplier_contact;

/*==============================================================*/
/* Table: xhs_supplier_contact                                  */
/*==============================================================*/
create table xhs_supplier_contact
(
   id                   varchar(50) not null comment '主键',
   supplier_id          varchar(50) comment '供应商id',
   name                 varchar(100) comment '联系人姓名',
   sex                  int comment '性别（0-男；1-女）',
   duty                 varchar(20) comment '职务',
   phone_number         varchar(20) comment '办公电话',
   mobile_number        varchar(20) comment '移动电话',
   email                varchar(50) comment '邮箱',
   qq_number            varchar(20) comment 'qq号码',
   webchat_number       varchar(50) comment '微信号',
   addr                 varchar(100) comment '地址',
   remarks              varchar(100) comment '备注',
   sort                 int comment '权重',
   status               int comment '状态（0-有效；1-删除）',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id),
   index(supplier_id)
);

alter table xhs_supplier_contact comment '供应商联系人信息';


drop table if exists xhs_supplier_mail;

/*==============================================================*/
/* Table: xhs_supplier_mail                                     */
/*==============================================================*/
create table xhs_supplier_mail
(
   id                   varchar(50) not null comment '主键id',
   supplier_id          varchar(50) comment '供应商id',
   mail_name            varchar(100) comment '收件人姓名',
   mail_addr            varchar(100) comment '收件人地址',
   mail_phone           varchar(20) comment '收件人电话',
   is_default           int comment '是否是默认地址（0-否；1-是）',
   sort                 int comment '权重',
   remarks              varchar(100) comment '备注',
   status               int comment '状态（0-有效；1-删除）',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id),
   index(supplier_id)
);

alter table xhs_supplier_mail comment '供应商物流地址信息';


drop table if exists xhs_customer_info;

/*==============================================================*/
/* Table: xhs_customer_info                                     */
/*==============================================================*/
create table xhs_customer_info
(
   id                   varchar(50) not null comment '主键',
   name                 varchar(100) comment '客户名称',
   addr                 varchar(200) comment '客户地址',
   phone_number         varchar(20) comment '办公电话',
   mobile_number        varchar(20) comment '移动电话',
   email                varchar(50) comment '电子邮件',
   qq_number            varchar(20) comment '企业QQ',
   webchat_number       varchar(50) comment '企业微信',
   remarks              varchar(500) comment '备注',
   sort                 int comment '权重',
   status               int comment '状态（0-有效；1-删除）',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id)
);

alter table xhs_customer_info comment '客户信息';

drop table if exists xhs_customer_contact;

/*==============================================================*/
/* Table: xhs_customer_contact                                  */
/*==============================================================*/
create table xhs_customer_contact
(
   id                   varchar(50) not null comment '主键',
   customer_id          varchar(50) comment '客户id',
   name                 varchar(100) comment '联系人姓名',
   sex                  int comment '性别（0-男；1-女）',
   duty                 varchar(20) comment '职务',
   phone_number         varchar(20) comment '办公电话',
   mobile_number        varchar(20) comment '移动电话',
   email                varchar(50) comment '邮箱',
   qq_number            varchar(20) comment 'qq号码',
   webchat_number       varchar(50) comment '微信号',
   addr                 varchar(100) comment '地址',
   remarks              varchar(100) comment '备注',
   sort                 int comment '权重',
   status               int comment '状态（0-有效；1-删除）',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id),
   index(customer_id)
);

alter table xhs_customer_contact comment '客户联系人信息';


drop table if exists xhs_customer_mail;

/*==============================================================*/
/* Table: xhs_customer_mail                                     */
/*==============================================================*/
create table xhs_customer_mail
(
   id                   varchar(50) not null comment '主键id',
   customer_id          varchar(50) comment '客户id',
   mail_name            varchar(100) comment '收件人姓名',
   mail_addr            varchar(100) comment '收件人地址',
   mail_phone           varchar(20) comment '收件人电话',
   is_default           int comment '是否是默认地址（0-否；1-是）',
   sort                 int comment '权重',
   remarks              varchar(100) comment '备注',
   status               int comment '状态（0-有效；1-删除）',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id),
   index(customer_id)
);

alter table xhs_customer_mail comment '客户物流地址信息';

drop table if exists xhs_warehouse_info;

/*==============================================================*/
/* Table: xhs_warehouse_info                                     */
/*==============================================================*/
create table xhs_warehouse_info
(
   id                   varchar(50) not null comment '主键',
   name                 varchar(100) comment '仓库名称',
   addr                 varchar(200) comment '仓库地址',
   remarks              varchar(500) comment '备注',
   sort                 int comment '权重',
   status               int comment '状态（0-有效；1-删除）',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id)
);

alter table xhs_warehouse_info comment '仓库信息';

drop table if exists xhs_warehouse_position;

/*==============================================================*/
/* Table: xhs_warehouse_position                                     */
/*==============================================================*/
create table xhs_warehouse_position
(
   id                   varchar(50) not null comment '主键',
   warehouse_id         varchar(50) comment '仓库id',
   pos_code             varchar(50) comment '区域编码',
   remarks              varchar(500) comment '备注',
   sort                 int comment '权重',
   status               int comment '状态（0-有效；1-删除）',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id),
   index(warehouse_id)
);

alter table xhs_warehouse_position comment '仓库分区信息';


drop table if exists xhs_product_info;

/*==============================================================*/
/* Table: xhs_product_info                                      */
/*==============================================================*/
create table xhs_product_info
(
   id                   varchar(50) not null comment '主键',
   product_code         varchar(20) comment '产品编码',
   product_name         varchar(100) comment '产品名称',
   sell_price           decimal(10,2) comment '产品销售单价',
   purchase_price       decimal(10,2) comment '产品采购单价',
   per                  int default 1 comment '单位数量',
   currency             varchar(10) comment '币种',
   remarks              varchar(200) comment '产品描述',
   status               int comment '状态',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id)
);

alter table xhs_product_info comment '产品信息表';


drop table if exists xhs_receiving_address;

/*==============================================================*/
/* Table: xhs_receiving_address                                 */
/*==============================================================*/
create table xhs_receiving_address
(
   id                   varchar(50) not null comment '主键id',
   receiver_name        varchar(100) comment '收件人姓名',
   receiver_addr        varchar(200) comment '收货地址',
   receiver_phone       varchar(20) comment '收货人电话号码',
   is_default           int comment '是否是默认收货地址（0-否；1-是）',
   remarks              varchar(200) comment '描述',
   status               int comment '状态',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id)
);

alter table xhs_receiving_address comment '收货地址信息';

drop table if exists xhs_stock_info;
/*==============================================================*/
/* Table: xhs_stock_info                                     */
/*==============================================================*/
create table xhs_stock_info
(
   id                   varchar(50) not null comment '入库单号',
   product_id           varchar(50) comment '产品id',
   warehouse_code       varchar(50) comment '入库仓库或分区编码',
   quantity             int default 0 comment '入库数量',
   remarks              varchar(200) comment '描述',
   status               int default 0 comment '状态',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id),
   unique(product_id,warehouse_code)
);

alter table xhs_stock_info comment '产品库存信息';

drop table if exists xhs_intostock_info;

/*==============================================================*/
/* Table: xhs_intostock_info                                     */
/*==============================================================*/
create table xhs_intostock_info
(
   id                   varchar(50) not null comment '入库单号',
   supplier_id          varchar(50) comment '供应商id',
   warehouse_code       varchar(50) comment '入库仓库或分区编码',
   product_num          int default 0 comment '入库产品种数',
   quantity             int default 0 comment '入库产品总数',
   remarks              varchar(200) comment '描述',
   instock_type         varchar(20) comment '入库类型',
   instock_status       varchar(20) comment '入库状态',
   status               int default 0 comment '状态',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id)
);

alter table xhs_intostock_info comment '入库登记信息';

drop table if exists xhs_intostock_product;

/*==============================================================*/
/* Table: xhs_intostock_product                                     */
/*==============================================================*/
create table xhs_intostock_product
(
   id                   varchar(50) not null comment '入库单号',
   intostock_id         varchar(50) comment '入库单id',
   product_id           varchar(50) comment '产品id',
   quantity             int default 0 comment '入库数量',
   remarks              varchar(200) comment '描述',
   status               int default 0 comment '状态',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id)
);

alter table xhs_intostock_product comment '入库产品列表信息';














drop table if exists xhs_warehouse_input;

/*==============================================================*/
/* Table: xhs_warehouse_input                                     */
/*==============================================================*/
create table xhs_warehouse_input
(
   id                   varchar(50) not null comment '入库单号',
   supplier_id          varchar(50) comment '供应商id',
   product_id           varchar(50) comment '产品id',
   input_price          decimal(10,2) comment '入库单价',
   currency             varchar(10) default 'CNY' comment '币种',
   input_per            int default 1 comment '入库单位数量',
   input_quantity       int comment '入库数量',
   warehouse_code       varchar(50) comment '入库仓库或分区编码',
   remarks              varchar(200) comment '描述',
   status               int default 0 comment '状态',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id)
);

alter table xhs_warehouse_input comment '入库登记信息';


drop table if exists xhs_warehouse_output;

/*==============================================================*/
/* Table: xhs_warehouse_output                                     */
/*==============================================================*/
create table xhs_warehouse_output
(
   id                   varchar(50) not null comment '出库单号',
   product_id           varchar(50) comment '产品id',
   output_price         decimal(10,2) comment '出库单价',
   currency             varchar(10) default 'CNY' comment '币种',
   output_per           int default 1 comment '出库单位数量',
   output_quantity      int comment '出库数量',
   warehouse_code       varchar(50) comment '出库仓库编码',
   customer_id          varchar(50) comment '客户id',
   remarks              varchar(200) comment '产品描述',
   output_status        int default 0 comment '出库状态',
   status               int default 0 comment '状态',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id)
);

alter table xhs_warehouse_output comment '出库信息';


drop table if exists xhs_warehouse_output_receiver;

/*==============================================================*/
/* Table: xhs_warehouse_output_receiver                                */
/*==============================================================*/
create table xhs_warehouse_output_receiver
(
   id                   varchar(50) not null comment '主键',
   output_id            varchar(50) comment '出库单号',
   receiver_name        varchar(100) comment '收件人姓名',
   receiver_addr        varchar(200) comment '收件人地址',
   receiver_phone       varchar(20) comment '收件人电话',
   remarks              varchar(200) comment '备注',
   status               int comment '状态',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id)
);

alter table xhs_warehouse_output_receiver comment '出库收货人信息';

drop table if exists xhs_warehouse_output_sender;

/*==============================================================*/
/* Table: xhs_warehouse_output_sender                                */
/*==============================================================*/
create table xhs_warehouse_output_sender
(
   id                   varchar(50) not null comment '主键',
   output_id            varchar(50) comment '出库单号',
   sender_name          varchar(100) comment '寄件人姓名',
   sender_addr          varchar(200) comment '寄件人地址',
   sender_phone         varchar(20) comment '寄件人电话',
   remarks              varchar(200) comment '备注',
   status               int comment '状态',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id)
);

alter table xhs_warehouse_output_sender comment '出库发货人信息';

drop table if exists xhs_warehouse_output_logistics;

/*==============================================================*/
/* Table: xhs_warehouse_output_logistics                                */
/*==============================================================*/
create table xhs_warehouse_output_logistics
(
   id                   varchar(50) not null comment '主键',
   output_id            varchar(50) comment '出货单号',
   mail_type            varchar(20) comment '快递类型',
   mail_number          varchar(50) comment '快递单号',
   remarks              varchar(200) comment '备注',
   status               int comment '状态',
   create_by            varchar(50) comment '创建者',
   create_date          timestamp default CURRENT_TIMESTAMP() comment '创建时间',
   update_by            varchar(50) comment '更新者',
   update_date          timestamp default CURRENT_TIMESTAMP() comment '更新时间',
   primary key (id)
);

alter table xhs_warehouse_output_logistics comment '出库物流信息';



