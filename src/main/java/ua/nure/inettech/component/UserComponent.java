package ua.nure.inettech.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ua.nure.inettech.client.FitnessClubClient;
import ua.nure.inettech.clientws.wsdl.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.stream.StreamResult;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Component
public class UserComponent {
    private FitnessClubClient client;

    @Autowired
    public UserComponent(FitnessClubClient client) {
        this.client = client;
    }
    @Bean
    StreamResult addNewUser() throws DatatypeConfigurationException {
        LocalDate localDate = LocalDate.of(2019, 4, 25);
        XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate.toString());
        User user = new User();
        user.setId(1);
        user.setUserName("Славик");
        user.setUserLastName("Сычев");
        user.setUserBirthdayDate(date);
        user.setUserInformation("Студент, учится в ХНУРЭ");
        Order order = new Order();
        order.setId(1);
        order.setOrderStartDateSubscription(date);
        order.setOrderEndDateSubscription(date);
        order.setOrderStatus(OrderStatus.АКТИВЕН);
        Subscription subscription = new Subscription();
        subscription.setId(1);
        subscription.setSubscriptionName("Student");
        BigDecimal decimal = new BigDecimal("260.00");
        subscription.setSubscriptionPrice(decimal);
        BigInteger count = new BigInteger("30");
        subscription.setSubscriptionCountVisits(count);
        Gym gym = new Gym();
        gym.setId(1);
        gym.setGymName("Тренажерный зал");
        gym.setGymMaximumUser(count);
        gym.setGymInformation("Тренажерный зал");
        System.out.println("");
        System.out.println("Add new user");
        return client.addUser(user, "+(38)093-258-26-25", order, subscription, gym);
    }
}
