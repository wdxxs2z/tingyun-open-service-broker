package com.wdxxs2z.servicebroker.tingyun.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.cloud.servicebroker.model.Catalog;
import org.springframework.cloud.servicebroker.model.DashboardClient;
import org.springframework.cloud.servicebroker.model.Plan;
import org.springframework.cloud.servicebroker.model.ServiceDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ConditionalOnWebApplication
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class SpringCloudCatalogConfig {

    @Bean
    public ServicesConfig servicesConfig() {
        return new ServicesConfig();
    }

    @Bean
    public Catalog catalog(ServicesConfig servicesConfig) {
        return new Catalog(
                servicesConfig
                        .getServices()
                        .stream()
                        .map(svc -> convertCatalogConfig(svc))
                        .collect(Collectors.toList())
        );
    }

    private ServiceDefinition convertCatalogConfig(CatalogConfig catalogConfig) {
        return new ServiceDefinition(
                catalogConfig.getId(),
                catalogConfig.getName(),
                catalogConfig.getDescription(),
                catalogConfig.isBindable(),
                catalogConfig.isPlan_updatable(),
                convertPlanConfig(catalogConfig.getPlans()),
                catalogConfig.getTags(),
                catalogConfig.getMetadata(),
                catalogConfig.getRequires(),
                convertDashboard(catalogConfig.getDashboard())
        );
    }

    private List<Plan> convertPlanConfig(List<PlanConfig> planConfigs) {
        return planConfigs
                .stream()
                .map(pc -> new Plan(
                        pc.getId(),
                        pc.getName(),
                        pc.getDescription(),
                        pc.getMetadata().get(),
                        pc.isFree()))
                .collect(Collectors.toList());
    }

    private DashboardClient convertDashboard(DashboardClientConfig dashboard) {
        return dashboard == null ? null :
                new DashboardClient(dashboard.getId(), dashboard.getSecret(), dashboard.getRedirect_uri());
    }


}
