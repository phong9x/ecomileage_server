package org.ecomileage.web.common;

import java.util.List;

public class PageCustom<T> {

	private List<T> list;
	private Long totalCount;
	private Integer current;
	private Integer size;
	private List<Object> other;
	private Object obj;

	public PageCustom(List<T> list, Long totalCount, Integer page, Integer size) {
		super();
		this.list = list;
		this.totalCount = totalCount;
		this.current = page;
		this.size = size;
	}

	public PageCustom(List<T> list, Long totalCount, Integer current, Integer size, List<Object> other) {
		super();
		this.list = list;
		this.totalCount = totalCount;
		this.current = current;
		this.size = size;
		this.other = other;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public List<Object> getOther() {
		return other;
	}

	public void setOther(List<Object> other) {
		this.other = other;
	}

	public Integer getTotalPages() {
		Double page = Math.ceil((double) totalCount / size);
		return page.intValue();
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

}
