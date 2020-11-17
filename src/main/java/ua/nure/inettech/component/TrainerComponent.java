package ua.nure.inettech.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ua.nure.inettech.client.FitnessClubClient;

import javax.xml.transform.stream.StreamResult;

@Component
public class TrainerComponent {
    private FitnessClubClient client;

    @Autowired
    public TrainerComponent(FitnessClubClient client) {
        this.client = client;
    }

    @Bean
    StreamResult getAllTrainers(){
        System.out.println("");
        System.out.println("List of trainers");
        return client.allTrainers();
    }
}
