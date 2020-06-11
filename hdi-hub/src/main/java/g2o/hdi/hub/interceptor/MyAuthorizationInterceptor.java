package g2o.hdi.hub.interceptor;

import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.interceptor.auth.IAuthRule;
import ca.uhn.fhir.rest.server.interceptor.auth.RuleBuilder;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Observation;

import java.util.List;

public class MyAuthorizationInterceptor extends ca.uhn.fhir.rest.server.interceptor.auth.AuthorizationInterceptor {
   @Override
   public List<IAuthRule> buildRuleList(RequestDetails theRequestDetails) {

      // Process this header
      String authHeader = theRequestDetails.getHeader("Authorization");
      // 1- Validate the JWT token
      // 2- APply authorization
      // 3- Register this interceptor in restful server
      
      
      System.out.println("eeeeeeeeeee");
      System.out.println(authHeader);

      RuleBuilder builder = new RuleBuilder();
      builder
         .allow().metadata().andThen()
         .allow().read().allResources().withAnyId().andThen()
         .allow().write().resourcesOfType(Observation.class).inCompartment("Patient", new IdType("Patient/123"));

      return builder.build();
   }

}
