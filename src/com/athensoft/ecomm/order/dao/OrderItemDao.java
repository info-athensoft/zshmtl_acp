package com.athensoft.ecomm.order.dao;

import java.util.List;

import com.athensoft.ecomm.order.entity.Order;
import com.athensoft.ecomm.order.entity.OrderItem;

public interface OrderItemDao {
	public List<OrderItem> findByOrderNo(long orderNo);
	public List<OrderItem> findByOrderNo(Order order);
}
