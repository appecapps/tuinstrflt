/*
 * Created on 28 ago 2017 ( Time 17:51:41 )
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
import com.tuin.bean.Entidadrol;
import com.tuin.business.service.EntidadrolService;
import com.tuin.web.listitem.EntidadrolListItem;

/**
 * Spring MVC controller for 'Entidadrol' management.
 */
@Controller
public class EntidadrolRestController {

	@Resource
	private EntidadrolService entidadrolService;
	
	@RequestMapping( value="/items/entidadrol",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<EntidadrolListItem> findAllAsListItems() {
		List<Entidadrol> list = entidadrolService.findAll();
		List<EntidadrolListItem> items = new LinkedList<EntidadrolListItem>();
		for ( Entidadrol entidadrol : list ) {
			items.add(new EntidadrolListItem( entidadrol ) );
		}
		return items;
	}
	
	@RequestMapping( value="/entidadrol",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Entidadrol> findAll() {
		return entidadrolService.findAll();
	}

	@RequestMapping( value="/entidadrol/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Entidadrol findOne(@PathVariable("id") Long id) {
		return entidadrolService.findById(id);
	}
	
	@RequestMapping( value="/entidadrol",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Entidadrol create(@RequestBody Entidadrol entidadrol) {
		return entidadrolService.create(entidadrol);
	}

	@RequestMapping( value="/entidadrol/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Entidadrol update(@PathVariable("id") Long id, @RequestBody Entidadrol entidadrol) {
		return entidadrolService.update(entidadrol);
	}

	@RequestMapping( value="/entidadrol/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		entidadrolService.delete(id);
	}
	
}
