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
import com.tuin.bean.Vehiculoservicio;
import com.tuin.business.service.VehiculoservicioService;
import com.tuin.web.listitem.VehiculoservicioListItem;

/**
 * Spring MVC controller for 'Vehiculoservicio' management.
 */
@Controller
public class VehiculoservicioRestController {

	@Resource
	private VehiculoservicioService vehiculoservicioService;
	
	@RequestMapping( value="/items/vehiculoservicio",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<VehiculoservicioListItem> findAllAsListItems() {
		List<Vehiculoservicio> list = vehiculoservicioService.findAll();
		List<VehiculoservicioListItem> items = new LinkedList<VehiculoservicioListItem>();
		for ( Vehiculoservicio vehiculoservicio : list ) {
			items.add(new VehiculoservicioListItem( vehiculoservicio ) );
		}
		return items;
	}
	
	@RequestMapping( value="/vehiculoservicio",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Vehiculoservicio> findAll() {
		return vehiculoservicioService.findAll();
	}

	@RequestMapping( value="/vehiculoservicio/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Vehiculoservicio findOne(@PathVariable("id") Long id) {
		return vehiculoservicioService.findById(id);
	}
	
	@RequestMapping( value="/vehiculoservicio",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Vehiculoservicio create(@RequestBody Vehiculoservicio vehiculoservicio) {
		return vehiculoservicioService.create(vehiculoservicio);
	}

	@RequestMapping( value="/vehiculoservicio/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Vehiculoservicio update(@PathVariable("id") Long id, @RequestBody Vehiculoservicio vehiculoservicio) {
		return vehiculoservicioService.update(vehiculoservicio);
	}

	@RequestMapping( value="/vehiculoservicio/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		vehiculoservicioService.delete(id);
	}
	
}
