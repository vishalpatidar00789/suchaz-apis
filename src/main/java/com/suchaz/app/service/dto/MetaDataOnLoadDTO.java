package com.suchaz.app.service.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * A DTO for the Loading MetaData at Page load.
 */
public class MetaDataOnLoadDTO implements Serializable {

   

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Map<String,ArrayList<Object>> metaDataOnLoadMap;

	public Map<String, ArrayList<Object>> getMetaDataOnLoadMap() {
		return metaDataOnLoadMap;
	}

	public void setMetaDataOnLoadMap(Map<String, ArrayList<Object>> metaDataOnLoadMap) {
		this.metaDataOnLoadMap = metaDataOnLoadMap;
	}
	

}
