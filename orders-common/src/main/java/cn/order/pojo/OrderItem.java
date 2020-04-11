/**
 * 
 */
package cn.order.pojo;

/** 	
 *	@Title BIG 1904 2组
 * 		@author 徐帆
 * 		@date 2019年8月12日 下午1:54:53
 * 		@version V1.0
 */
public class OrderItem {
	private int iid;
	private int oid;
	private int fid;
	private String fname;
	private double price;
	private int num;
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "OrderItem [iid=" + iid + ", oid=" + oid + ", fid=" + fid + ", fname=" + fname + ", price=" + price
				+ ", num=" + num + "]";
	}
	
}
