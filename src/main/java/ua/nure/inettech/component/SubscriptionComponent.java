package ua.nure.inettech.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ua.nure.inettech.client.FitnessClubClient;

import javax.xml.transform.stream.StreamResult;

@Component
public class SubscriptionComponent {

    private FitnessClubClient client;

    @Autowired
    public SubscriptionComponent(FitnessClubClient client) {
        this.client = client;
    }
    @Bean
    StreamResult getAllSubscription(){
        System.out.println("");
        System.out.println("List of subscriptions");
        return client.allSubscription();
    }
}
