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
import com.tuin.bean.Clienteviaje;
import com.tuin.business.service.ClienteviajeService;
import com.tuin.web.listitem.ClienteviajeListItem;

/**
 * Spring MVC controller for 'Clienteviaje' management.
 */
@Controller
public class ClienteviajeRestController {

	@Resource
	private ClienteviajeService clienteviajeService;
	
	@RequestMapping( value="/items/clienteviaje",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClienteviajeListItem> findAllAsListItems() {
		List<Clienteviaje> list = clienteviajeService.findAll();
		List<ClienteviajeListItem> items = new LinkedList<ClienteviajeListItem>();
		for ( Clienteviaje clienteviaje : list ) {
			items.add(new ClienteviajeListItem( clienteviaje ) );
		}
		return items;
	}
	
	@RequestMapping( value="/clienteviaje",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Clienteviaje> findAll() {
		return clienteviajeService.findAll();
	}

	@RequestMapping( value="/clienteviaje/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Clienteviaje findOne(@PathVariable("id") Long id) {
		return clienteviajeService.findById(id);
	}
	
	@RequestMapping( value="/clienteviaje",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Clienteviaje create(@RequestBody Clienteviaje clienteviaje) {
		return clienteviajeService.create(clienteviaje);
	}

	@RequestMapping( value="/clienteviaje/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Clienteviaje update(@PathVariable("id") Long id, @RequestBody Clienteviaje clienteviaje) {
		return clienteviajeService.update(clienteviaje);
	}

	@RequestMapping( value="/clienteviaje/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		clienteviajeService.delete(id);
	}
	
}
