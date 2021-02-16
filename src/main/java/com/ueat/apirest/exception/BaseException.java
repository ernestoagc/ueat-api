package com.ueat.apirest.exception;

import java.util.List;
import java.util.Map;

public abstract class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1606430053395108926L;

	private String titulo;
	private String codigoMensaje;
	private transient Object[] args;
	private transient List<Map<String, Object>> errores;

	Exception exception;
	
	private static final String TITULO_DEFECTO = "General Error";

	public BaseException(String codigoMensaje, Object[] args) {
		this.titulo = TITULO_DEFECTO;
		this.codigoMensaje = codigoMensaje;
		this.args = args;
	}

	public BaseException(String titulo, String codigoMensaje, Object[] args) {
		this.titulo = titulo;
		this.codigoMensaje = codigoMensaje;
		this.args = args;
	}
	public BaseException(String titulo, String codigoMensaje, List<Map<String, Object>> errores) {
		this.titulo = titulo;
		this.codigoMensaje = codigoMensaje;
		this.setErrores(errores);
	}

	public BaseException(String codigoMensaje, Object[] args, Throwable cause) {
		super(cause);
		this.titulo = TITULO_DEFECTO;
		this.codigoMensaje = codigoMensaje;
		this.args = args;
	}

	public BaseException(String titulo, String codigoMensaje, Object[] args, Throwable cause) {
		super(cause);
		this.titulo = titulo;
		this.codigoMensaje = codigoMensaje;
		this.args = args;
	}
	
	

	public String getTitulo() {
		return titulo;
	}

	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	public Object[] getArgs() {
		return args;
	}

	protected void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	protected void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}

	protected void setArgs(Object[] args) {
		this.args = args;
	}

	private String mensajeAux;
	private String mensaje;

	public BaseException(String codigoMensaje, String mensaje, String mensajeAux) {
		this.titulo = TITULO_DEFECTO;
		this.codigoMensaje = codigoMensaje;
		this.mensajeAux = mensajeAux;
		this.mensaje = mensaje;
	}
	
	public BaseException(String codigoMensaje, String mensaje) {
		this.titulo = TITULO_DEFECTO;
		this.codigoMensaje = codigoMensaje;
		this.mensaje = mensaje;
	}


	public String getMensajeAux() {
		return mensajeAux;
	}

	public void setMensajeAux(String mensajeAux) {
		this.mensajeAux = mensajeAux;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<Map<String, Object>> getErrores() {
		return errores;
	}

	public void setErrores(List<Map<String, Object>> errores) {
		this.errores = errores;
	}

}