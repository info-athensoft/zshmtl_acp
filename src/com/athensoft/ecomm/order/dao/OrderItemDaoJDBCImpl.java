package com.athensoft.ecomm.order.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.athensoft.ecomm.order.entity.Order;
import com.athensoft.ecomm.order.entity.OrderItem;

@Component
@Qualifier("orderItemDaoJDBCImpl")
public class OrderItemDaoJDBCImpl implements OrderItemDao {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public List<OrderItem> findByOrderId(long orderId) {
		String sql = "select * from order_item where order_id =:order_id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("order_id", orderId);
		List<OrderItem> x = null;
		try{
			x = jdbc.query(sql, paramSource, new OrderItemRowMapper());
		}catch(Exception ex){
			x = null;
		}
		return x;
	}

	@Override
	public List<OrderItem> findByOrderId(Order order) {
		long orderId = 0;
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
				
		if(order!=null){
			orderId = order.getOrderId();
			orderItemList = findByOrderId(orderId);
		}
		return orderItemList;
	}
	
	private static class OrderItemRowMapper implements RowMapper<OrderItem>{
		public OrderItem mapRow(ResultSet rs, int rowNumber) throws SQLException {
			OrderItem x = new OrderItem();
			x.setOrderId(rs.getLong("order_id"));
			x.setOrderItemId(rs.getLong("order_item_id"));
			x.setSeqNo(rs.getInt("seqno"));
			x.setItemId(rs.getLong("item_id"));
			x.setItemName(rs.getString("item_name"));
			x.setItemDesc(rs.getString("item_desc"));
			x.setQuantity(rs.getInt("quantity"));
			x.setUnit(rs.getString("unit"));
			x.setUnitPrice(rs.getDouble("unit_price"));
			x.setAmount(rs.getDouble("amount"));
            return x;
		}		
	}

}
