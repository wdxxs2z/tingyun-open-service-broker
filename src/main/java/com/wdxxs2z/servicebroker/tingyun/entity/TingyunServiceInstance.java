package com.wdxxs2z.servicebroker.tingyun.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name="service_instance")
public class TingyunServiceInstance {
	
	@Id
	@Column(name = "instance_id", unique = true, nullable = false, length = 42)
	private String instanceId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tingyunServiceInstance")
	private Set<TingyunServiceInstanceBinding> tingyunServiceInstanceBindings = new HashSet<TingyunServiceInstanceBinding>(0);
	
	@CreatedDate
	private Date createDate;
	
	@Column(name = "license_key", nullable = false, length = 36)
	private String licenseKey;

	public TingyunServiceInstance() {}

	public TingyunServiceInstance(String instanceId, Set<TingyunServiceInstanceBinding> tingyunServiceInstanceBindings, Date createDate, String licenseKey) {
		this.instanceId = instanceId;
		this.tingyunServiceInstanceBindings = tingyunServiceInstanceBindings;
		this.createDate = createDate;
		this.licenseKey = licenseKey;
	}

	public TingyunServiceInstance(String instanceId, Date date, String licenseKey) {
		super();
		this.instanceId = instanceId;
		this.createDate = date;
		this.licenseKey = licenseKey;
	}

	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

	public Set<TingyunServiceInstanceBinding> getTingyunServiceInstanceBindings() {
		return tingyunServiceInstanceBindings;
	}

	public void setTingyunServiceInstanceBindings(Set<TingyunServiceInstanceBinding> tingyunServiceInstanceBindings) {
		this.tingyunServiceInstanceBindings = tingyunServiceInstanceBindings;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
}
