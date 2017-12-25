package org.ecomileage.bean;

import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Array;

public class TrackingList {
	private ArrayList<Tracking> list = new ArrayList<>();

	public ArrayList<Tracking> getList() {
		return list;
	}

	public void setList(ArrayList<Tracking> list) {
		this.list = list;
	}
	
}
