/*
 * Created on 28 ago 2017 ( Time 17:51:39 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.rest.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.tuin.bean.Clienteviajeservicio;
import com.tuin.business.service.ClienteviajeservicioService;
import com.tuin.web.listitem.ClienteviajeservicioListItem;

/**
 * Spring MVC controller for 'Clienteviajeservicio' management.
 */
@Controller
public class ClienteviajeservicioRestController {

	@Resource
	private ClienteviajeservicioService clienteviajeservicioService;
	
	@RequestMapping( value="/items/clienteviajeservicio",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClienteviajeservicioListItem> findAllAsListItems() {
		List<Clienteviajeservicio> list = clienteviajeservicioService.findAll();
		List<ClienteviajeservicioListItem> items = new LinkedList<ClienteviajeservicioListItem>();
		for ( Clienteviajeservicio clienteviajeservicio : list ) {
			items.add(new ClienteviajeservicioListItem( clienteviajeservicio ) );
		}
		return items;
	}
	
	@RequestMapping( value="/clienteviajeservicio",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Clienteviajeservicio> findAll() {
		return clienteviajeservicioService.findAll();
	}

	@RequestMapping( value="/clienteviajeservicio/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Clienteviajeservicio findOne(@PathVariable("id") Long id) {
		return clienteviajeservicioService.findById(id);
	}
	
	@RequestMapping( value="/clienteviajeservicio",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Clienteviajeservicio create(@RequestBody Clienteviajeservicio clienteviajeservicio) {
		return clienteviajeservicioService.create(clienteviajeservicio);
	}

	@RequestMapping( value="/clienteviajeservicio/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Clienteviajeservicio update(@PathVariable("id") Long id, @RequestBody Clienteviajeservicio clienteviajeservicio) {
		return clienteviajeservicioService.update(clienteviajeservicio);
	}

	@RequestMapping( value="/clienteviajeservicio/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		clienteviajeservicioService.delete(id);
	}
	
}