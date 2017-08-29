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
import com.tuin.bean.Variables;
import com.tuin.business.service.VariablesService;
import com.tuin.web.listitem.VariablesListItem;

/**
 * Spring MVC controller for 'Variables' management.
 */
@Controller
public class VariablesRestController {

	@Resource
	private VariablesService variablesService;
	
	@RequestMapping( value="/items/variables",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<VariablesListItem> findAllAsListItems() {
		List<Variables> list = variablesService.findAll();
		List<VariablesListItem> items = new LinkedList<VariablesListItem>();
		for ( Variables variables : list ) {
			items.add(new VariablesListItem( variables ) );
		}
		return items;
	}
	
	@RequestMapping( value="/variables",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Variables> findAll() {
		return variablesService.findAll();
	}

	@RequestMapping( value="/variables/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Variables findOne(@PathVariable("id") Long id) {
		return variablesService.findById(id);
	}
	
	@RequestMapping( value="/variables",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Variables create(@RequestBody Variables variables) {
		return variablesService.create(variables);
	}

	@RequestMapping( value="/variables/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Variables update(@PathVariable("id") Long id, @RequestBody Variables variables) {
		return variablesService.update(variables);
	}

	@RequestMapping( value="/variables/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		variablesService.delete(id);
	}
	
}