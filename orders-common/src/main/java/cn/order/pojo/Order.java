/**
 * 
 */
package cn.order.pojo;

import java.util.Date;
import java.util.List;

/** 	
 *	@Title BIG 1904 2组
 * 		@author 徐帆
 * 		@date 2019年8月9日 下午1:50:03
 * 		@version V1.0
 */
public class Order {
	private int oid;
	private int uid;
	private double totalprice;
	private Date createtime;
	private List<OrderItem> orderitems;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public List<OrderItem> getOrderitems() {
		return orderitems;
	}
	public void setOrderitems(List<OrderItem> orderitems) {
		this.orderitems = orderitems;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", uid=" + uid + ", totalprice=" + totalprice + ", createtime=" + createtime
				+ ", orderitems=" + orderitems + "]";
	}
	
	
}
