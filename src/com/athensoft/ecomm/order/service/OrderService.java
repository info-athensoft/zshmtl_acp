package com.athensoft.ecomm.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.athensoft.ecomm.order.dao.OrderDao;
import com.athensoft.ecomm.order.dao.OrderItemDao;
import com.athensoft.ecomm.order.entity.Order;

@Service
public class OrderService {
	private OrderDao orderDao;
	
	private OrderItemDao orderItemDao;
	
	@Autowired
	@Qualifier("orderDaoJDBCImpl")
	public void setOrderao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	@Autowired
	@Qualifier("orderItemDaoJDBCImpl")
	public void setOrderItemDao(OrderItemDao orderItemDao) {
		this.orderItemDao = orderItemDao;
	}
	
	public Order getOrderByOrderId(long orderId){
		Order order = new Order();
		order = orderDao.findById(orderId);		
		order.setOrderItems(orderItemDao.findByOrderId(orderId));
		return order;
	}
	
	/* Test method, not real business */
	public List<Order> getAllOrders(){
		List<Order> orders = orderDao.findAll();
		//TODO: to populate order items for each later
		return orders;
	}
	
	
}
