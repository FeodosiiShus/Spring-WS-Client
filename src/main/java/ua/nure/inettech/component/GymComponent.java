package ua.nure.inettech.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ua.nure.inettech.client.FitnessClubClient;

import javax.xml.transform.stream.StreamResult;

@Component
public class GymComponent {

    private FitnessClubClient client;

    @Autowired
    public GymComponent(FitnessClubClient client) {
        this.client = client;
    }

    @Bean
    StreamResult allGyms(){
        System.out.println("Get all gyms");
        return client.AllGyms();
    }

    @Bean
    StreamResult findGym(){
        System.out.println();
        String gymName = "Malibu";
        System.out.println("Find gym by name "+gymName);
        return client.findGymByName(gymName);
    }

    @Bean
    StreamResult addNewGym(){
        System.out.println("");
        System.out.println("Add new Gym");
        return client.addNewGym(10, "Тренажерный зал", 40, "Множество тренажеров");
    }
}
