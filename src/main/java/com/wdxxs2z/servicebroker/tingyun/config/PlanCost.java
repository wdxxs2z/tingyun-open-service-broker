package com.wdxxs2z.servicebroker.tingyun.config;

import java.util.Map;

import org.springframework.cloud.servicebroker.model.EmptyListSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanCost {

    @JsonSerialize(nullsUsing = EmptyListSerializer.class)
    private Map<String,String> amount;

    @JsonSerialize
    private String unit;

    public Map<String, String> getAmount() {
        return amount;
    }

    public void setAmount(Map<String, String> amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
