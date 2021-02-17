package com.ueat.apirest.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ueat.apirest.controller.BaseController; 
import com.ueat.apirest.dto.MessageResponseDto;
import com.ueat.apirest.exception.ValidacionException;
import com.ueat.apirest.util.Constante;

@ControllerAdvice(basePackages={"com.soaint.apirest.controlador"})
public class BaseController {
	
	private static Logger LOGGER = LogManager.getLogger(BaseController.class);
	
	
	  @Autowired
	  private MessageSource messageSource;
	  String defaultMessage = "Mensaje no especificado";
	
	  @ExceptionHandler({Exception.class})
	  @ResponseBody
	  public MessageResponseDto generalException(HttpServletRequest request, HttpServletResponse response, Exception ex)
	  {
		  response.setContentType("application/json;charset=UTF-8");
		    if ((ex instanceof ValidacionException)) {
		      return resolverException(request, response, (ValidacionException)ex);
		    }
		
		return resolverException(request, response, ex);
	  }
	
	protected ObjectMapper getObjectMapper()
	  {

		ObjectMapper objectMapper = new ObjectMapper();
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	    objectMapper.setDateFormat(df);
	    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    objectMapper.setSerializationInclusion(Include.NON_NULL);
	    //objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	    return objectMapper;
	  }
	
	 public MessageResponseDto resolverException(HttpServletRequest request, HttpServletResponse response, ValidacionException ex)
	  {
		  //Parametro paramRequestID = this.configuracionService.obtenerParametroxCodigo("REQUEST_ID");
		  String ticket = request.getHeader("REQUEST_ID");
	    if ((ex.getErrores() != null) && (ex.getErrores().size() > 0)) {
	    	String codigos="";
	    	String mensajes="";
	      for (Map<String, Object> error : ex.getErrores()) {
	    	String codigoError=(String)error.get("codigoError");
	    	if(Constante.CODIGOS_ERRORES.NO_DEVOLVIO_RESULTADO_OK.equals(codigoError)){
	    		addError(response, codigoError, (String)error.get("mensaje"), 204, new Object[0]);
	    	}else{
	    		codigos+=codigoError+"|";
	    		mensajes+=(String)error.get("mensaje")+"|";    		
	    	}        
	      }
	      if(!codigos.equals("")){
	    	  addError(response, codigos.substring(0,codigos.length()-1), mensajes.substring(0,mensajes.length()-1), 409, new Object[0]);
	      }
	    } else if (Constante.CODIGOS_ERRORES.NO_DEVOLVIO_RESULTADO_OK.equals(ex.getCodigoMensaje())) {
	    	LOGGER.error("{}| A004 - INICIO", new Object[] { ticket });
	  	  	LOGGER.error("{}|",ex.getMessage(), ex, new Object[] { ticket });
	  	  	LOGGER.error(ticket, ex);
	  	  	LOGGER.error("{}| A004 - FIN", new Object[] { ticket });
	      addError(response, ex.getCodigoMensaje(),Constante.MENSAJE_ERRORES.NO_DEVOLVIO_RESULTADO_OK, 204, new Object[0]);
	    } else if (Constante.CODIGOS_ERRORES.CAMPOS_OBLIGATORIOS.equals(ex.getCodigoMensaje()) || Constante.CODIGOS_ERRORES.BAD_REQUEST.equals(ex.getCodigoMensaje())) {
	    	LOGGER.error("{}| - INICIO", new Object[] { ticket });
	  	  	LOGGER.error("{}|",ex.getMensaje(), ex, new Object[] { ticket });
	  	  	LOGGER.error(ticket, ex);
	  	  	LOGGER.error("{}| - FIN", new Object[] { ticket });
	      addError(response, ex.getCodigoMensaje(),ex.getMensaje(), 400, new Object[0]);
	    }   else {
	    	LOGGER.error("{}| else - INICIO", new Object[] { ticket });
	  	  	LOGGER.error(ticket +"|"+ex.getCodigoMensaje()+" "+ex.getMensaje(), ex);
	  	  	LOGGER.error(ticket, ex);
	  	  	LOGGER.error("{}| else - FIN", new Object[] { ticket });
	      addError(response, ex.getCodigoMensaje(), 409, ex.getArgs());
	    }
	    return null;
	  }
	 
	 public void addError(HttpServletResponse response, String code, int status, Object... parametro)
	  {
	    addError(response, code, null, status, parametro);
	  }
	 
	
	  public MessageResponseDto resolverException(HttpServletRequest request, HttpServletResponse response, Exception ex)
	  {
		String ticket = request.getHeader("REQUEST_ID");
		LOGGER.error("{}| ERROR NO CONTROLADO - INICIO", new Object[] { ticket });
	    LOGGER.error(ticket, ex);
	    LOGGER.error("{}| ERROR NO CONTROLADO - FIN", new Object[] { ticket });
	    String codigo = null;
	    String mensaje = null;
		    codigo = "technicalError";
	    if ((ex instanceof NullPointerException)) {
	      mensaje = "Error nulo";
	    } else {
	      mensaje = ex.getMessage();
	    }
	    addError(response, codigo, mensaje, 500, new Object[0]);
	    return null;
	  }
	  
	  public void addError(HttpServletResponse response, String code, String mensaje, int status, Object... parametro)
	  {
	    if (mensaje == null) {
	      mensaje = this.messageSource.getMessage(code, parametro, this.defaultMessage, Locale.getDefault());
	    }
	    response.addHeader("errorCode", code);
	    response.addHeader("errorMessage", mensaje);
	    response.setStatus(status);
	  }

}
