package com.zjdd.report.current.dao;

import java.util.Map;

public interface SystemParamsDAO {

	public String getParamsValues( String paramsKey );
	
	public Integer updateParamsValues( Map<String, Object> params );
	
}
