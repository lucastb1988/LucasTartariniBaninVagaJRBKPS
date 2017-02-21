package br.com.lucas.pharma.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

// http://localhost:8080/Pharma1/NomedoRestful
@ApplicationPath("rest")
public class PharmaResourceConfig extends ResourceConfig {

	public PharmaResourceConfig() {
		packages("br.com.lucas.pharma.service");
	}
}

//Service ou Serviço é um método(é comum devolver respostas / enviar parâmetros depende da situação)

//É comum utilizar protocolos de GET, POST, PUT, DELETE