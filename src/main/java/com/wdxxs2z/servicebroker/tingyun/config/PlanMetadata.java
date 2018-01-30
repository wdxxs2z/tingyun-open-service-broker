package com.wdxxs2z.servicebroker.tingyun.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.servicebroker.model.EmptyListSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanMetadata {

    @JsonSerialize(nullsUsing = EmptyListSerializer.class)
    private List<String> bullets;

    @JsonSerialize(nullsUsing = EmptyListSerializer.class)
    private List<PlanCost> costs;

    public List<String> getBullets() {
        return bullets;
    }

    public void setBullets(List<String> bullets) {
        this.bullets = bullets;
    }

    public List<PlanCost> getCosts() {
        return costs;
    }

    public void setCosts(List<PlanCost> costs) {
        this.costs = costs;
    }

    public Map<String, Object> get() {
        Map<String, Object> map = new HashMap<>();
        map.put("bullets", bullets);
        map.put("costs", costs);
        return map;
    }
}
