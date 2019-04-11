/* Query 1, 2 */
insert into product(product_id, product_name, product_price, product_type, product_desc)
values('112-2-05','삼성TV 40인치',2000000,'곡선형','새로나온 삼성 TV');
insert into product(product_id, product_name, product_price, product_type, product_desc)
values('112-2-06','삼성TV 45인치',3000000,'곡선형','새로나온 삼성 TV');
insert into product(product_id, product_name, product_price, product_type, product_desc)
values('112-2-07','삼성TV 50인치',5000000,'평면형','새로나온 삼성 TV');
insert into product(product_id, product_name, product_price, product_type, product_desc)
values('112-2-08','삼성 냉장고 지펠 500L',2000000,'양문형','새로나온 삼성 냉장고');
insert into product(product_id, product_name, product_price, product_type, product_desc)
values('112-2-09','삼성 냉장고 지펠 600L',3500000,'양문형','새로나온 삼성 냉장고');
insert into product(product_id, product_name, product_price, product_type, product_desc)
values('112-2-10','삼성 냉장고 지펠 700L',4000000,'양문형','새로나온 삼성 냉장고');
 
/* Query 3 */ 
select product_id, product_name, (product_price*0.85), product_type, product_desc as sale_product from product;

/* Query 4 */
update product set product_price=product_price*0.8
where product_name like '%TV%';
select * from product;

/* Query 5 */
select sum(product_price) from product;