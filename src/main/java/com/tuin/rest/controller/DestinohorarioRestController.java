/*
 * Created on 28 ago 2017 ( Time 17:51:40 )
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
import com.tuin.bean.Destinohorario;
import com.tuin.business.service.DestinohorarioService;
import com.tuin.web.listitem.DestinohorarioListItem;

/**
 * Spring MVC controller for 'Destinohorario' management.
 */
@Controller
public class DestinohorarioRestController {

	@Resource
	private DestinohorarioService destinohorarioService;
	
	@RequestMapping( value="/items/destinohorario",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<DestinohorarioListItem> findAllAsListItems() {
		List<Destinohorario> list = destinohorarioService.findAll();
		List<DestinohorarioListItem> items = new LinkedList<DestinohorarioListItem>();
		for ( Destinohorario destinohorario : list ) {
			items.add(new DestinohorarioListItem( destinohorario ) );
		}
		return items;
	}
	
	@RequestMapping( value="/destinohorario",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Destinohorario> findAll() {
		return destinohorarioService.findAll();
	}

	@RequestMapping( value="/destinohorario/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Destinohorario findOne(@PathVariable("id") Long id) {
		return destinohorarioService.findById(id);
	}
	
	@RequestMapping( value="/destinohorario",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Destinohorario create(@RequestBody Destinohorario destinohorario) {
		return destinohorarioService.create(destinohorario);
	}

	@RequestMapping( value="/destinohorario/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Destinohorario update(@PathVariable("id") Long id, @RequestBody Destinohorario destinohorario) {
		return destinohorarioService.update(destinohorario);
	}

	@RequestMapping( value="/destinohorario/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		destinohorarioService.delete(id);
	}
	
}
