package ua.nure.inettech.client;

import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import ua.nure.inettech.clientws.wsdl.*;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

public class FitnessClubClient extends WebServiceGatewaySupport {
    private static final String MESSAGE_ALLGYMS =
            "<GymList xmlns=\"http://inettech.nure.ua\"></GymList>";
    private static final String MESSAGE_GYMNAME_START =
            "<Gyms xmlns=\"http://inettech.nure.ua\">" +
                    "<gymName>";
    private static final String MESSAGE_GYMNAME_END = "</gymName>" + "</Gyms>";

    private static final String ALL_SUBSCRIPTION = "<SubscriptionList xmlns=\"http://inettech.nure.ua\"></SubscriptionList>";

    private static final String ALL_TRAINER = "<TrainerList xmlns=\"http://inettech.nure.ua\"></TrainerList>";

    private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    public StreamResult AllGyms() {
        StreamSource source = new StreamSource(new StringReader(MESSAGE_ALLGYMS));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:80/ws/fitnessclub",
                source, new SoapActionCallback("http://inettech.nure.ua/GymList"), result);
        return result;
    }

    public StreamResult findGymByName(String name) {
        StreamSource source = new StreamSource(new StringReader(MESSAGE_GYMNAME_START+name+MESSAGE_GYMNAME_END));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:80/ws/fitnessclub",
                source, new SoapActionCallback("http://inettech.nure.ua/Gyms"), result);
        return result;
    }

    public StreamResult addNewGym(int id, String name, Integer maxUsers, String information){
        StreamSource source = new StreamSource(new StringReader(addGymSettings(id, name, maxUsers, information)));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:80/ws/fitnessclub",
                source, new SoapActionCallback("http://inettech.nure.ua/AddGym"), result);
        return result;
    }

    public StreamResult allSubscription(){
        StreamSource source = new StreamSource(new StringReader(ALL_SUBSCRIPTION));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:80/ws/fitnessclub",
                source, new SoapActionCallback("http://inettech.nure.ua/SubscriptionList"), result);
        return result;
    }

    public StreamResult allTrainers(){
        StreamSource source = new StreamSource(new StringReader(ALL_TRAINER));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:80/ws/fitnessclub",
                source, new SoapActionCallback("http://inettech.nure.ua/TrainerList"), result);
        return result;
    }

    public StreamResult addUser(User user, String phone, Order order, Subscription subscription, Gym gym){
        StreamSource source = new StreamSource(new StringReader(addUserSettings(user, phone, order, subscription, gym)));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:80/ws/fitnessclub",
                source, new SoapActionCallback("http://inettech.nure.ua/AddUser"), result);
        return result;
    }

    public String addGymSettings(int id, String name, Integer maxUsers, String information){
        StringBuilder str = new StringBuilder();
        str.append("<AddGym id=\"+"+id+"+\" xmlns=\"http://inettech.nure.ua\">" +
                "<gymName>"+name+"</gymName>" +
                "<gymMaximumUser>"+maxUsers.toString()+"</gymMaximumUser>" +
                "<gymInformation>"+ information +"</gymInformation>" +
                "</AddGym>");
        return str.toString();
    }

    public String addUserSettings(User user, String phone, Order order, Subscription subscription, Gym gym){
        StringBuilder str = new StringBuilder();
        str.append("<AddUser id=\""+user.getId()+"\" xmlns=\"http://inettech.nure.ua\">" +
                "            <userName>"+user.getUserName()+"</userName>" +
                "            <userLastName>"+user.getUserLastName()+"</userLastName>" +
                "            <userBirthdayDate>"+user.getUserBirthdayDate()+"</userBirthdayDate>" +
                "            <userInformation>"+user.getUserInformation()+"</userInformation>" +
                "            <phones>" +
                "                <phone>"+phone+"</phone>" +
                "            </phones>" +
                "            <order id=\""+order.getId()+"\">" +
                "                <orderSubscription id=\""+subscription.getId()+"\">" +
                "                    <subscriptionName>"+subscription.getSubscriptionName()+"</subscriptionName>" +
                "                    <subscriptionPrice>"+subscription.getSubscriptionPrice().toString()+"</subscriptionPrice>" +
                "                    <subscriptionCountVisits>"+subscription.getSubscriptionCountVisits().toString()+"</subscriptionCountVisits>" +
                "                    <gyms>" +
                "                        <!--1 or more repetitions:-->" +
                "                        <gym id=\""+gym.getId()+"\">" +
                "                            <gymName>"+gym.getGymName()+"</gymName>" +
                "                            <gymMaximumUser>"+gym.getGymMaximumUser().toString()+"</gymMaximumUser>" +
                "                            <gymInformation>"+gym.getGymInformation()+"</gymInformation>" +
                "                        </gym>" +
                "                    </gyms>" +
                "                </orderSubscription>" +
                "                <orderStartDateSubscription>"+order.getOrderStartDateSubscription()+"</orderStartDateSubscription>" +
                "                <orderEndDateSubscription>"+order.getOrderEndDateSubscription()+"</orderEndDateSubscription>" +
                "                <orderStatus>"+order.getOrderStatus()+"</orderStatus>" +
                "</order>" +
                "</AddUser>");
        return str.toString();
    }
}
