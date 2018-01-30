package com.wdxxs2z.servicebroker.tingyun.service;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceDoesNotExistException;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceExistsException;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.GetLastServiceOperationRequest;
import org.springframework.cloud.servicebroker.model.GetLastServiceOperationResponse;
import org.springframework.cloud.servicebroker.model.OperationState;
import org.springframework.cloud.servicebroker.model.UpdateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.UpdateServiceInstanceResponse;
import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Component;

import com.wdxxs2z.servicebroker.tingyun.config.ServicesConfig;
import com.wdxxs2z.servicebroker.tingyun.entity.TingyunServiceInstance;
import com.wdxxs2z.servicebroker.tingyun.repository.ServiceInstanceRepository;

@Component
public class TingyunApmServiceInstanceService implements ServiceInstanceService{
	
	private Log log = LogFactory.getLog(TingyunApmServiceInstanceService.class);
	
	@Autowired
	private ServiceInstanceRepository serviceInstanceRepository;
	
	@Autowired
	private ServicesConfig servicesConfig;
	
	private String licenseKey = "";

	@Override
	public CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest request) {
		String serviceInstanceId = request.getServiceInstanceId();
		
		log.info("Checking the service instance({ " + serviceInstanceId + " })'s license key.");
		
		if (request.getParameters() != null && request.getParameters().containsKey("license_key")) {
			licenseKey = (String) request.getParameters().get("license_key");
		}else {
			servicesConfig.getServices().forEach(s -> {
				if(s.getId().equals(request.getServiceDefinitionId())) {
					s.getPlans().forEach(p -> {
						if(p.getId().equals(request.getPlanId())) {
							licenseKey = p.getLicensekey();
						}
					});
				}
			});
		}
		
		
		if (serviceInstanceRepository.exists(serviceInstanceId)) {
			throw new ServiceInstanceExistsException(serviceInstanceId,
			           request.getServiceDefinitionId());
		}
		
		log.info("Creating Service Instance({ " + serviceInstanceId + " })");
		
		TingyunServiceInstance serviceInstance = new TingyunServiceInstance(
				serviceInstanceId, new Date(), licenseKey);
		
		log.info("Created Service Instance({ " + serviceInstanceId + " })");
		serviceInstanceRepository.save(serviceInstance);
		
		return new CreateServiceInstanceResponse();
	}

	@Override
	public GetLastServiceOperationResponse getLastOperation(GetLastServiceOperationRequest request) {
		String serviceInstanceId = request.getServiceInstanceId();
        log.info("Get Last Service Operation({ " + serviceInstanceId + "})");
        return new GetLastServiceOperationResponse().withOperationState(OperationState.SUCCEEDED);
	}

	@Override
	public DeleteServiceInstanceResponse deleteServiceInstance(DeleteServiceInstanceRequest request) {
		String serviceInstanceId = request.getServiceInstanceId();
		if (!serviceInstanceRepository.exists(serviceInstanceId)) {
			throw new ServiceInstanceDoesNotExistException(serviceInstanceId);
		}
		serviceInstanceRepository.delete(serviceInstanceId);
		log.info("Deleted Service Instance({ " + serviceInstanceId + "})");
		return new DeleteServiceInstanceResponse();
	}

	@Override
	public UpdateServiceInstanceResponse updateServiceInstance(UpdateServiceInstanceRequest request) {
		String serviceInstanceId = request.getServiceInstanceId();
        log.info("Update Service Instance({ " + serviceInstanceId + "})");
        if (request.getParameters() != null && request.getParameters().containsKey("license_key")) {
			licenseKey = (String) request.getParameters().get("license_key");
			if (serviceInstanceRepository.exists(serviceInstanceId)) {
				throw new ServiceInstanceExistsException(serviceInstanceId,
				           request.getServiceDefinitionId());
			}
			TingyunServiceInstance serviceInstance = new TingyunServiceInstance(
					serviceInstanceId, new Date(), licenseKey);
			
			log.info("Created Service Instance({ " + serviceInstanceId + " })");
			serviceInstanceRepository.save(serviceInstance);
		}
        
        return new UpdateServiceInstanceResponse();
	}

}
