package org.moita.sbe.jmx;

import java.util.Collections;
import java.util.Optional;

import org.moita.sbe.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@ManagedResource(objectName="org.moita.jmx:name=ServerManager", description="Server manager.")
public class ManagerController {
	
	@Autowired
	private Service service;
	
	@ManagedOperation(description="Get item by its ID.")
	@ManagedOperationParameters({
		@ManagedOperationParameter(name="id", description= "Item's ID")})
	public String getItemById(Long id) {
		Assert.isTrue(id.compareTo(1L) > 0, "ID must to be bigger than zero");
		return Optional.ofNullable(service.get(id)).map(i -> i.toString()).orElse("ID " + id + " does not exists!");
	}
	
	@ManagedOperation(description="Get a list of items.")
	public String getItems() {
		return Optional.ofNullable(service.list()).orElse(Collections.emptyList()).toString();
	}
}
