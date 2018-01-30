package com.wdxxs2z.servicebroker.tingyun.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name="service_instance_binding")
public class TingyunServiceInstanceBinding {
	
	@Id
	@Column(name = "binding_id", unique = true, nullable = false, length = 42)
	private String bindingId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instanceid", nullable = false)
	private TingyunServiceInstance tingyunServiceInstance;
	
	@CreatedDate
	private Date createDate;

	public TingyunServiceInstanceBinding() {}

	public TingyunServiceInstanceBinding(String bindingId, TingyunServiceInstance tingyunServiceInstance, Date createDate) {
		this.bindingId = bindingId;
		this.tingyunServiceInstance = tingyunServiceInstance;
		this.createDate = createDate;
	}

	public String getBindingId() {
		return bindingId;
	}

	public void setBindingId(String bindingId) {
		this.bindingId = bindingId;
	}

	public TingyunServiceInstance getTingyunServiceInstance() {
		return tingyunServiceInstance;
	}

	public void setTingyunServiceInstance(TingyunServiceInstance tingyunServiceInstance) {
		this.tingyunServiceInstance = tingyunServiceInstance;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
