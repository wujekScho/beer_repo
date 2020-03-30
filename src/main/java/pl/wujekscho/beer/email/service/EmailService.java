package pl.wujekscho.beer.email.service;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import lombok.extern.slf4j.Slf4j;
import pl.wujekscho.beer.email.dto.EmailEvent;
import pl.wujekscho.beer.email.entity.EmailType;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
@Slf4j
public class EmailService {

    @Inject
    Mailer mailer;

    public void emailEventObserver(@Valid @Observes EmailEvent emailEvent) {
        log.info("Event email of type: [{}] acquired", emailEvent.getType());
        getRunnableMap(emailEvent).get(emailEvent.getType()).run();
    }

    public void sendRegistrationEmail(EmailEvent event) {
        String recipient = event.getRecipient();
        mailer.send(Mail.withText(recipient, "Welcome to beer-repo",
                "Activation link: http://localhost:8081/authorization/confirm-registration/" + event.getToken()));
        log.info("Registration email sent to user: {}", recipient);
    }

    private Map<EmailType, Runnable> getRunnableMap(EmailEvent emailEvent) {
        HashMap<EmailType, Runnable> mailMap = new HashMap<>();
        mailMap.put(EmailType.REGISTRATION, () -> sendRegistrationEmail(emailEvent));
        return mailMap;
    }
}
