package com.ueat.apirest.util;

public abstract class Constante  {
	
	public static final class CODIGOS_ERRORES	{
		public final static String DEVOLVIO_RESULTADO_ERROR	= "A001";
		public final static String SERVICIO_WEB_NO_DISPONIBLE			= "technicalError";
		public final static String BAD_REQUEST					= "A003"; //400
		public final static String NO_DEVOLVIO_RESULTADO_OK		= "A004";
		public final static String USUARIO_NO_EXISTE					= "A005";
		public final static String SIN_RESULTADOS						= "A006"; //200
		public final static String CAMPOS_OBLIGATORIOS						= "A007"; //400
		
		
	}	
	
	public static final class MENSAJE_ERRORES	{
		public final static String DEVOLVIO_RESULTADO_ERROR	= "A001";
		public final static String SERVICIO_WEB_NO_DISPONIBLE			= "technicalError";
		public final static String BAD_REQUEST					= "el objeto "; //400
		public final static String NO_DEVOLVIO_RESULTADO_OK		= "No results";
		public final static String USUARIO_NO_EXISTE					= "A005";
		public final static String CAMPOS_OBLIGATORIOS						= "Falta ingresar campo obligatorio: {0}"; //400
		
		
	}	

}
