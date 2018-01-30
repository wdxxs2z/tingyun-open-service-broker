package com.wdxxs2z.servicebroker.tingyun.config;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.servicebroker.model.EmptyListSerializer;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
@Configuration
@ConfigurationProperties(prefix = "cloudfoundry.servicebroker.catalog")
public class ServicesConfig {

    @JsonSerialize(nullsUsing = EmptyListSerializer.class)
    @NotNull
    @Valid
    private List<CatalogConfig> services;

    public List<CatalogConfig> getServices() {
        return services;
    }

    public void setServices(List<CatalogConfig> services) {
        this.services = services;
    }
}
