# Tingyun APM Service Broker Example

## Premise

* Tingyun apm agent or buildpack with tingyun: https://github.com/wdxxs2z/java-buildpack

## Design

* Store the service register metedata in mysql,so push the service broker with mysql service.
* Free plan should set a license key,if not set it,we must create service instance with param(license_key:xxxx)

## Configuration

1.Push the service broker to paas
```
cf push tingyun-service-broker --no-start
cf set-env tingyun-service-broker SERVICE_BROKER_USERNAME admin
cf set-env tingyun-service-broker SERVICE_BROKER_PASSWORD changeme
|default plan need a license key,otherwise you need create instance with para license_key
cf set-env tingyun-service-broker TINGYUN_LICENSE_KEY xxxxx
```
2.Bind the mysql service instance
```
cf bind-service tingyun-service-broker mysql-instance
```
3.Start tingyun-service-broker
```
cf start tingyun-service-broker
```
4.Create Service broker
```
cf create-service-broker tingyun-apm-service-broker admin changeme http://tingyun-service-broker.domain.io
```
5.Access the service broker plan
```
cf enable-service-access tingyun-apm-service-broker
```
6.Create the apm service instance(or with params)
```
cf create-service tingyun-apm-service-broker free apm-example -c "{"license_key": "xxxxxx"}"
```
7.Push app with tingyun buildpack,and bind the service to application
```
cf push app_name -b tingyun_buildpack
cf bind-service app_name apm-example
```
8.Check your application environment
```
 "tingyun-apm-service-broker": [
        {
          "credentials": {
            "license_key": "xxxxxxxxxxx"
          },
          "syslog_drain_url": null,
          "volume_mounts": [],
          "label": "tingyun-apm-service-broker",
          "provider": null,
          "plan": "free",
          "name": "zk-instance",
          "tags": [
            "tingyun",
            "tingyun_apm"
          ]
        }
      ]
```
9.Restage application
```
cf restage app_name

-----> Downloading Tingyun Agent 2.5.0 from http://192.168.213.130:9999/packages/tingyun/tingyun-2.5.0.jar (found in cache)
```
10.Check the apm monit data in website