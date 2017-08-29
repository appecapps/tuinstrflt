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
import com.tuin.bean.Facturaelectronica;
import com.tuin.business.service.FacturaelectronicaService;
import com.tuin.web.listitem.FacturaelectronicaListItem;

/**
 * Spring MVC controller for 'Facturaelectronica' management.
 */
@Controller
public class FacturaelectronicaRestController {

	@Resource
	private FacturaelectronicaService facturaelectronicaService;
	
	@RequestMapping( value="/items/facturaelectronica",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<FacturaelectronicaListItem> findAllAsListItems() {
		List<Facturaelectronica> list = facturaelectronicaService.findAll();
		List<FacturaelectronicaListItem> items = new LinkedList<FacturaelectronicaListItem>();
		for ( Facturaelectronica facturaelectronica : list ) {
			items.add(new FacturaelectronicaListItem( facturaelectronica ) );
		}
		return items;
	}
	
	@RequestMapping( value="/facturaelectronica",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Facturaelectronica> findAll() {
		return facturaelectronicaService.findAll();
	}

	@RequestMapping( value="/facturaelectronica/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Facturaelectronica findOne(@PathVariable("id") Long id) {
		return facturaelectronicaService.findById(id);
	}
	
	@RequestMapping( value="/facturaelectronica",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Facturaelectronica create(@RequestBody Facturaelectronica facturaelectronica) {
		return facturaelectronicaService.create(facturaelectronica);
	}

	@RequestMapping( value="/facturaelectronica/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Facturaelectronica update(@PathVariable("id") Long id, @RequestBody Facturaelectronica facturaelectronica) {
		return facturaelectronicaService.update(facturaelectronica);
	}

	@RequestMapping( value="/facturaelectronica/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		facturaelectronicaService.delete(id);
	}
	
}
