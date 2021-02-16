package com.ueat.apirest.exception;

import java.util.List;
import java.util.Map;

import com.ueat.apirest.exception.*;

public class ValidacionException extends BaseException {

	private static final long serialVersionUID = 2558693133828625388L;

	private static final String TITULO_DEFECTO_VALIDACION = "Validación de servicios";

	public ValidacionException(String codigoMensaje, Object[] args) {
		super(TITULO_DEFECTO_VALIDACION, codigoMensaje, args);
	}
	
	public ValidacionException(String codigoMensaje, List<Map<String, Object>> args) {
		super(TITULO_DEFECTO_VALIDACION, codigoMensaje, args);
	}
	

	public ValidacionException(String titulo, String codigoMensaje, Object[] args) {
		super(titulo, codigoMensaje, args);
	}

	public ValidacionException(String codigoMensaje, Object[] args, Throwable cause) {
		super(TITULO_DEFECTO_VALIDACION, codigoMensaje, args, cause);
	}

	public ValidacionException(String codigoMensaje, String mensaje) {
		super(codigoMensaje, mensaje, mensaje);
	}
	
	public ValidacionException(String codigoMensaje, String mensaje, String mensajeAux) {
		super(codigoMensaje, mensaje);
	}
}
