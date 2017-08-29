/*
 * Created on 28 ago 2017 ( Time 17:51:47 )
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
import com.tuin.bean.Tipovehiculo;
import com.tuin.business.service.TipovehiculoService;
import com.tuin.web.listitem.TipovehiculoListItem;

/**
 * Spring MVC controller for 'Tipovehiculo' management.
 */
@Controller
public class TipovehiculoRestController {

	@Resource
	private TipovehiculoService tipovehiculoService;
	
	@RequestMapping( value="/items/tipovehiculo",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TipovehiculoListItem> findAllAsListItems() {
		List<Tipovehiculo> list = tipovehiculoService.findAll();
		List<TipovehiculoListItem> items = new LinkedList<TipovehiculoListItem>();
		for ( Tipovehiculo tipovehiculo : list ) {
			items.add(new TipovehiculoListItem( tipovehiculo ) );
		}
		return items;
	}
	
	@RequestMapping( value="/tipovehiculo",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Tipovehiculo> findAll() {
		return tipovehiculoService.findAll();
	}

	@RequestMapping( value="/tipovehiculo/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Tipovehiculo findOne(@PathVariable("id") Long id) {
		return tipovehiculoService.findById(id);
	}
	
	@RequestMapping( value="/tipovehiculo",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Tipovehiculo create(@RequestBody Tipovehiculo tipovehiculo) {
		return tipovehiculoService.create(tipovehiculo);
	}

	@RequestMapping( value="/tipovehiculo/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Tipovehiculo update(@PathVariable("id") Long id, @RequestBody Tipovehiculo tipovehiculo) {
		return tipovehiculoService.update(tipovehiculo);
	}

	@RequestMapping( value="/tipovehiculo/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		tipovehiculoService.delete(id);
	}
	
}
