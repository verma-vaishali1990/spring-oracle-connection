package oracle.gcp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import oracle.gcp.services.RegistryService;

@RestController
public class RegistryController {

    private RegistryService registryService;
   
		
		  @Autowired public void setRegistryService(RegistryService registryService) {
		  this.registryService = registryService; }
		 

    @GetMapping(value = "/getConnection")
    public void list(){
    	 System.out.println("Trying to connect to DB");
         registryService.connectToDB();
         
    }

}
