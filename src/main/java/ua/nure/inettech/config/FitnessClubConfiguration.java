package ua.nure.inettech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import ua.nure.inettech.client.FitnessClubClient;

@Configuration
public class FitnessClubConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ua.nure.inettech.clientws.wsdl");
        return marshaller;
    }

    @Bean
    public FitnessClubClient fitnessClubClient(Jaxb2Marshaller marshaller){
        FitnessClubClient client = new FitnessClubClient();
        client.setDefaultUri("http://localhost:80/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
