package com.example.helloworld;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.helloworld.resources.HelloWorldResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;



public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
        
        /*
        try {

			Client client = Client.create();
			client.addFilter(new HTTPBasicAuthFilter("jkalra", "d46b6537bf434320edbad45783561d78faf5a646"));
			
				   
			WebResource webResource = client
			   .resource("https://github.digitalriverws.net/api/v3/repos/jkalra/global-commerce-modules/git/tags");

			String input = "{\"tag\": \"TestTag2\",\"message\": \"This is Test Tag\",\"object\": \"3ad52fceb95f4106f9077e80fc01fc047b09fbcc\"}";	

			ClientResponse response = webResource.type("application/json")
			   .post(ClientResponse.class, input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}

			System.out.println("*****************Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		  } catch (Exception e) {

			e.printStackTrace();

		  }*/
        
        
        
        
        javax.ws.rs.client.Client client = ClientBuilder.newClient();
        client.register(new Authenticator("jkalra", "d46b6537bf434320edbad45783561d78faf5a646"));
        WebTarget target = client.target("https://github.digitalriverws.net/api/v3/repos/jkalra/global-commerce-modules/git/tags");
        String shaId = "3ad52fceb95f4106f9077e80fc01fc047b09fbcc";
        String tag = "{\"tag\": \"TestSrush\",\"message\": \"Test Tag 2\",\"object\": \""+shaId+"\",\"type\": \"commit\",\"tagger\": {\"name\": \"Srushti Khadke\",\"email\": \"skhadke@digitalriver.com\",\"date\": \"2016-08-02T18:07:00+05:30\"}}";
        Response response =target.request(MediaType.APPLICATION_JSON).post(Entity.json(tag));
        String responseStringSecond = response.readEntity(String.class);
        System.out.println("responseStringSecond :"+responseStringSecond);
        System.out.println("Status :"+response.getStatus());
        
        
        
        WebTarget target1 = client.target("https://github.digitalriverws.net/api/v3/repos/jkalra/global-commerce-modules/git/refs");
        String tag1 = "{\"ref\": \"refs/tags/TestSrush\",\"sha\": \"6da2acaaa8350381387a089f56c7cc0279213896\"}";
        Response response1 =target1.request(MediaType.APPLICATION_JSON).post(Entity.json(tag1));
        System.out.println("Status2 :"+response1.getStatus());
        
        
        
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {

    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {
    	System.out.println("*****************Output**********\n");
    	final HelloWorldResource resource = new HelloWorldResource(
    	        configuration.getTemplate(),
    	        configuration.getDefaultName()
    	    );
    	    environment.jersey().register(resource);
    }

}