package g2o.hdi.hub.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.ResponseHighlighterInterceptor;
import g2o.hdi.hub.interceptor.MyAuthorizationInterceptor;
import g2o.hdi.hub.interceptor.MyCorsInterceptor;
import g2o.hdi.hub.provider.PatientProvider;

@WebServlet("/*")
public class SimpleRestfulServer extends RestfulServer {

	@Override
	protected void initialize() throws ServletException {
		// Create a context for the appropriate version
		setFhirContext(FhirContext.forR4());
		
		// Register resource providers
		registerProvider(new PatientProvider());
		
		// Format the responses in nice HTML
		registerInterceptor(new ResponseHighlighterInterceptor());
		
		// enable CORS
		registerInterceptor(new MyCorsInterceptor());
		registerInterceptor(new MyAuthorizationInterceptor());
		
		
	}
}
