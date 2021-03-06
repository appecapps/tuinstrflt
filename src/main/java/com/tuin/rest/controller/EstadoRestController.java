/*
 * Created on 28 ago 2017 ( Time 17:51:42 )
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
import com.tuin.bean.Estado;
import com.tuin.business.service.EstadoService;
import com.tuin.web.listitem.EstadoListItem;

/**
 * Spring MVC controller for 'Estado' management.
 */
@Controller
public class EstadoRestController {

	@Resource
	private EstadoService estadoService;
	
	@RequestMapping( value="/items/estado",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<EstadoListItem> findAllAsListItems() {
		List<Estado> list = estadoService.findAll();
		List<EstadoListItem> items = new LinkedList<EstadoListItem>();
		for ( Estado estado : list ) {
			items.add(new EstadoListItem( estado ) );
		}
		return items;
	}
	
	@RequestMapping( value="/estado",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Estado> findAll() {
		return estadoService.findAll();
	}

	@RequestMapping( value="/estado/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Estado findOne(@PathVariable("id") Long id) {
		return estadoService.findById(id);
	}
	
	@RequestMapping( value="/estado",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Estado create(@RequestBody Estado estado) {
		return estadoService.create(estado);
	}

	@RequestMapping( value="/estado/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Estado update(@PathVariable("id") Long id, @RequestBody Estado estado) {
		return estadoService.update(estado);
	}

	@RequestMapping( value="/estado/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		estadoService.delete(id);
	}
	
}
