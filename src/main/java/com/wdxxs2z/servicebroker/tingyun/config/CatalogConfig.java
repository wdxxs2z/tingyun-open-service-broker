package com.wdxxs2z.servicebroker.tingyun.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.cloud.servicebroker.model.EmptyListSerializer;
import org.springframework.cloud.servicebroker.model.EmptyMapSerializer;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatalogConfig {

    @JsonSerialize
    @NotNull
    @NotEmpty
    private String name;

    @JsonSerialize
    @NotNull
    @NotEmpty
    private String id;

    @JsonSerialize
    @NotNull
    @NotEmpty
    private String description;

    @JsonSerialize(nullsUsing = EmptyListSerializer.class)
    private List<String> tags;

    @JsonSerialize(nullsUsing = EmptyListSerializer.class)
    private List<String> requires;

    @JsonSerialize
    @NotNull
    private Boolean bindable;

    @JsonSerialize(nullsUsing = EmptyMapSerializer.class)
    private Map<String, Object> metadata;

    @JsonSerialize
    private DashboardClientConfig dashboard;

    @JsonSerialize
    @JsonProperty("plan_updateable")
    private boolean plan_updatable;

    @JsonIgnore
    @NotNull
    @NotEmpty
    @Valid
    private List<PlanConfig> plans;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getRequires() {
        return requires;
    }

    public void setRequires(List<String> requires) {
        this.requires = requires;
    }

    public boolean isBindable() {
        return bindable;
    }

    public void setBindable(boolean bindable) {
        this.bindable = bindable;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public DashboardClientConfig getDashboard() {
        return dashboard;
    }

    public void setDashboard(DashboardClientConfig dashboard) {
        this.dashboard = dashboard;
    }

    public boolean isPlan_updatable() {
        return plan_updatable;
    }

    public void setPlan_updatable(boolean plan_updatable) {
        this.plan_updatable = plan_updatable;
    }

    public List<PlanConfig> getPlans() {
        return plans;
    }

    public void setPlans(List<PlanConfig> plans) {
        this.plans = plans;
    }

}