/*
 * Created on 28 ago 2017 ( Time 17:51:45 )
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
import com.tuin.bean.Provincia;
import com.tuin.business.service.ProvinciaService;
import com.tuin.web.listitem.ProvinciaListItem;

/**
 * Spring MVC controller for 'Provincia' management.
 */
@Controller
public class ProvinciaRestController {

	@Resource
	private ProvinciaService provinciaService;
	
	@RequestMapping( value="/items/provincia",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ProvinciaListItem> findAllAsListItems() {
		List<Provincia> list = provinciaService.findAll();
		List<ProvinciaListItem> items = new LinkedList<ProvinciaListItem>();
		for ( Provincia provincia : list ) {
			items.add(new ProvinciaListItem( provincia ) );
		}
		return items;
	}
	
	@RequestMapping( value="/provincia",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Provincia> findAll() {
		return provinciaService.findAll();
	}

	@RequestMapping( value="/provincia/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Provincia findOne(@PathVariable("id") Long id) {
		return provinciaService.findById(id);
	}
	
	@RequestMapping( value="/provincia",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Provincia create(@RequestBody Provincia provincia) {
		return provinciaService.create(provincia);
	}

	@RequestMapping( value="/provincia/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Provincia update(@PathVariable("id") Long id, @RequestBody Provincia provincia) {
		return provinciaService.update(provincia);
	}

	@RequestMapping( value="/provincia/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		provinciaService.delete(id);
	}
	
}
