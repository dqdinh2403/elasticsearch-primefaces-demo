package com.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter (value = "updatedDateConverter")
public class UpdatedDateConverter implements Converter {
	
	private static String DATE_FORMAT = "yyyy-MM-dd hh:mm";

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Date date = (Date) value;
		return new SimpleDateFormat(DATE_FORMAT).format(date);
	}

}
