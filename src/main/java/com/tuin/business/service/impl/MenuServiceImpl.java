/*
 * Created on 28 ago 2017 ( Time 17:51:17 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tuin.bean.Menu;
import com.tuin.bean.jpa.MenuEntity;
import java.util.List;
import com.tuin.business.service.MenuService;
import com.tuin.business.service.mapping.MenuServiceMapper;
import com.tuin.persistence.PersistenceServiceProvider;
import com.tuin.persistence.services.MenuPersistence;
import org.springframework.stereotype.Component;

/**
 * Implementation of MenuService
 */
@Component
public class MenuServiceImpl implements MenuService {

	private MenuPersistence menuPersistence;

	@Resource
	private MenuServiceMapper menuServiceMapper;
	
	public MenuServiceImpl() {
		menuPersistence = PersistenceServiceProvider.getService(MenuPersistence.class);
	}
		
	@Override
	public Menu findById(Long id) {
		MenuEntity entity = menuPersistence.load(id);
		return menuServiceMapper.mapMenuEntityToMenu(entity);
	}

	@Override
	public List<Menu> findAll() {
		List<MenuEntity> entities = menuPersistence.loadAll();
		List<Menu> beans = new ArrayList<Menu>();
		for(MenuEntity entity : entities) {
			beans.add(menuServiceMapper.mapMenuEntityToMenu(entity));
		}
		return beans;
	}

	@Override
	public Menu save(Menu menu) {
		return update(menu) ;
	}

	@Override
	public Menu create(Menu menu) {
		if(menuPersistence.load(menu.getId()) != null) {
			throw new IllegalStateException("already.exists");
		}
		MenuEntity menuEntity = new MenuEntity();
		menuServiceMapper.mapMenuToMenuEntity(menu, menuEntity);
		MenuEntity menuEntitySaved = menuPersistence.save(menuEntity);
		return menuServiceMapper.mapMenuEntityToMenu(menuEntitySaved);
	}

	@Override
	public Menu update(Menu menu) {
		MenuEntity menuEntity = menuPersistence.load(menu.getId());
		menuServiceMapper.mapMenuToMenuEntity(menu, menuEntity);
		MenuEntity menuEntitySaved = menuPersistence.save(menuEntity);
		return menuServiceMapper.mapMenuEntityToMenu(menuEntitySaved);
	}

	@Override
	public void delete(Long id) {
		menuPersistence.delete(id);
	}

	public MenuPersistence getMenuPersistence() {
		return menuPersistence;
	}

	public void setMenuPersistence(MenuPersistence menuPersistence) {
		this.menuPersistence = menuPersistence;
	}

	public MenuServiceMapper getMenuServiceMapper() {
		return menuServiceMapper;
	}

	public void setMenuServiceMapper(MenuServiceMapper menuServiceMapper) {
		this.menuServiceMapper = menuServiceMapper;
	}

}
