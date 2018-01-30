package com.wdxxs2z.servicebroker.tingyun.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceBindingExistsException;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceAppBindingResponse;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.stereotype.Component;

import com.wdxxs2z.servicebroker.tingyun.entity.TingyunServiceInstanceBinding;
import com.wdxxs2z.servicebroker.tingyun.repository.ServiceInstanceBindingRepository;
import com.wdxxs2z.servicebroker.tingyun.repository.ServiceInstanceRepository;

@Component
public class TingyunApmServiceInstanceBindingService implements ServiceInstanceBindingService{
	
	private Log log = LogFactory.getLog(TingyunApmServiceInstanceBindingService.class);
	
	@Autowired
	private ServiceInstanceRepository sip;
	
	@Autowired
	private ServiceInstanceBindingRepository sibp;
	
	@Override
	public CreateServiceInstanceBindingResponse createServiceInstanceBinding(
			CreateServiceInstanceBindingRequest request) {
		
		String serviceInstanceId = request.getServiceInstanceId();
        String bindingId = request.getBindingId();
        Map<String, Object> credentials = new HashMap<String, Object>();
        
        log.info("Creating Service Binding({ " + bindingId + "})" + " for Service({ " + serviceInstanceId + " })");
 
        if (sibp.exists(bindingId)) {
        	throw new ServiceInstanceBindingExistsException(serviceInstanceId,
        	        bindingId);
        }
        
        credentials.put("license_key", sip.findOne(serviceInstanceId).getLicenseKey());
        
        TingyunServiceInstanceBinding serviceInstanceBinding = new TingyunServiceInstanceBinding(bindingId, sip.findOne(serviceInstanceId), new Date());
        
        sibp.save(serviceInstanceBinding);
        
        log.info("Created Service Binding({ " + bindingId + "})" + " for Service({ " + serviceInstanceId + " })");
        
		return new CreateServiceInstanceAppBindingResponse().withCredentials(credentials);
	}

	@Override
	public void deleteServiceInstanceBinding(DeleteServiceInstanceBindingRequest request) {
		String bindingId = request.getBindingId();
		String serviceInstanceId = request.getServiceInstanceId();
		if (sibp.exists(bindingId)) {
			sibp.delete(bindingId);
			log.info("Deleted Service Binding({ " + " })" + bindingId + " for Service({ " + serviceInstanceId + " })");     
        }
	}

}
