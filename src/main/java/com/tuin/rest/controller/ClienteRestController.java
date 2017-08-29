/*
 * Created on 28 ago 2017 ( Time 17:51:38 )
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
import com.tuin.bean.Cliente;
import com.tuin.business.service.ClienteService;
import com.tuin.web.listitem.ClienteListItem;

/**
 * Spring MVC controller for 'Cliente' management.
 */
@Controller
public class ClienteRestController {

	@Resource
	private ClienteService clienteService;
	
	@RequestMapping( value="/items/cliente",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClienteListItem> findAllAsListItems() {
		List<Cliente> list = clienteService.findAll();
		List<ClienteListItem> items = new LinkedList<ClienteListItem>();
		for ( Cliente cliente : list ) {
			items.add(new ClienteListItem( cliente ) );
		}
		return items;
	}
	
	@RequestMapping( value="/cliente",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Cliente> findAll() {
		return clienteService.findAll();
	}

	@RequestMapping( value="/cliente/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Cliente findOne(@PathVariable("id") Long id) {
		return clienteService.findById(id);
	}
	
	@RequestMapping( value="/cliente",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Cliente create(@RequestBody Cliente cliente) {
		return clienteService.create(cliente);
	}

	@RequestMapping( value="/cliente/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Cliente update(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
		return clienteService.update(cliente);
	}

	@RequestMapping( value="/cliente/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		clienteService.delete(id);
	}
	
}
