package com.wdxxs2z.servicebroker.tingyun.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanConfig {

    @JsonSerialize
    @NotNull
    @NotEmpty
    private String id;

    @JsonSerialize
    @NotNull
    @NotEmpty
    private String name;

    @JsonSerialize
    @NotNull
    @NotEmpty
    private String description;
    
    @JsonSerialize
    @NotNull
    @NotEmpty
    private String licensekey;

    @JsonSerialize
    private PlanMetadata metadata;

    @JsonSerialize
    private boolean bindable;

    @JsonSerialize
    private boolean free;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PlanMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(PlanMetadata metadata) {
        this.metadata = metadata;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public boolean getBindable() {
        return bindable;
    }

    public void setBindable(boolean bindable) {
        this.bindable = bindable;
    }

	public String getLicensekey() {
		return licensekey;
	}

	public void setLicensekey(String licensekey) {
		this.licensekey = licensekey;
	}
}
