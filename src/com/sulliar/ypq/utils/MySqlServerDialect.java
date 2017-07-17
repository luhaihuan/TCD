package com.sulliar.ypq.utils;

import java.sql.Types;

import org.hibernate.dialect.SQLServerDialect;
import org.hibernate.type.StringType;

public class MySqlServerDialect extends SQLServerDialect {
	public MySqlServerDialect(){
		super();
		registerColumnType(Types.NVARCHAR, 8000,"nvarchar($1)");
		registerColumnType(Types.NVARCHAR, "nvarchar(255)");
	}
}
