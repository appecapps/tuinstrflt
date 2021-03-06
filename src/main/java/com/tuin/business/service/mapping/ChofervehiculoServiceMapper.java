/*
 * Created on 28 ago 2017 ( Time 17:51:12 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.tuin.bean.Chofervehiculo;
import com.tuin.bean.jpa.ChofervehiculoEntity;
import com.tuin.bean.jpa.ChoferEntity;
import com.tuin.bean.jpa.VehiculoEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class ChofervehiculoServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public ChofervehiculoServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'ChofervehiculoEntity' to 'Chofervehiculo'
	 * @param chofervehiculoEntity
	 */
	public Chofervehiculo mapChofervehiculoEntityToChofervehiculo(ChofervehiculoEntity chofervehiculoEntity) {
		if(chofervehiculoEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Chofervehiculo chofervehiculo = map(chofervehiculoEntity, Chofervehiculo.class);

		//--- Link mapping ( link to Chofer )
		if(chofervehiculoEntity.getChofer() != null) {
			chofervehiculo.setChoferid(chofervehiculoEntity.getChofer().getId());
		}
		//--- Link mapping ( link to Vehiculo )
		if(chofervehiculoEntity.getVehiculo() != null) {
			chofervehiculo.setVehiculoid(chofervehiculoEntity.getVehiculo().getId());
		}
		return chofervehiculo;
	}
	
	/**
	 * Mapping from 'Chofervehiculo' to 'ChofervehiculoEntity'
	 * @param chofervehiculo
	 * @param chofervehiculoEntity
	 */
	public void mapChofervehiculoToChofervehiculoEntity(Chofervehiculo chofervehiculo, ChofervehiculoEntity chofervehiculoEntity) {
		if(chofervehiculo == null) {
			return;
		}

		//--- Generic mapping 
		map(chofervehiculo, chofervehiculoEntity);

		//--- Link mapping ( link : chofervehiculo )
		if( hasLinkToChofer(chofervehiculo) ) {
			ChoferEntity chofer1 = new ChoferEntity();
			chofer1.setId( chofervehiculo.getChoferid() );
			chofervehiculoEntity.setChofer( chofer1 );
		} else {
			chofervehiculoEntity.setChofer( null );
		}

		//--- Link mapping ( link : chofervehiculo )
		if( hasLinkToVehiculo(chofervehiculo) ) {
			VehiculoEntity vehiculo2 = new VehiculoEntity();
			vehiculo2.setId( chofervehiculo.getVehiculoid() );
			chofervehiculoEntity.setVehiculo( vehiculo2 );
		} else {
			chofervehiculoEntity.setVehiculo( null );
		}

	}
	
	/**
	 * Verify that Chofer id is valid.
	 * @param Chofer Chofer
	 * @return boolean
	 */
	private boolean hasLinkToChofer(Chofervehiculo chofervehiculo) {
		if(chofervehiculo.getChoferid() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Verify that Vehiculo id is valid.
	 * @param Vehiculo Vehiculo
	 * @return boolean
	 */
	private boolean hasLinkToVehiculo(Chofervehiculo chofervehiculo) {
		if(chofervehiculo.getVehiculoid() != null) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}