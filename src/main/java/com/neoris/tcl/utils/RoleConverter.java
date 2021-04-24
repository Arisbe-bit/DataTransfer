package com.neoris.tcl.utils;

import javax.annotation.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.springframework.web.context.annotation.RequestScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.neoris.tcl.security.models.Rol;

@FacesConverter(forClass = Rol.class)
@ManagedBean
@RequestScope
public class RoleConverter implements Converter<Rol> {

	private final static Logger LOG = LoggerFactory.getLogger(RoleConverter.class);

	@Override
	public Rol getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		LOG.debug("[String to Rol] conver to {}", value);
		Rol rol = null;
		if (value != null) {
			rol = Rol.valueOf(value);
		}
		return rol;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Rol value) throws ConverterException {
		LOG.debug("[Rol to String] convert to {}", value);
		return value.name();
	}

}
