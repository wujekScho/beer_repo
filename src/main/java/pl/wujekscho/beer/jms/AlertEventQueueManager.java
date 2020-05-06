package pl.wujekscho.beer.jms;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@ApplicationScoped
@Slf4j
public class AlertEventQueueManager {
    private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DESTINATION = "java:/jms/queue/usageAlertQueue";
    private static final String LOGIN = "queueUser";
    private static final String PASSWORD = "simoniot";
    private static final String QUEUE_ADDRESS = "https-remoting://simoniot.zalassconsulting.pl:3381";

    public void send(UsageAlertEvent event) {
        Context namingContext = null;
        JMSContext context = null;

        try {
            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            env.put(Context.PROVIDER_URL, QUEUE_ADDRESS);
            env.put(Context.SECURITY_PRINCIPAL, LOGIN);
            env.put(Context.SECURITY_CREDENTIALS, PASSWORD);
            namingContext = new InitialContext(env);

            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(CONNECTION_FACTORY);
            log.info("Got connection factory: {}", CONNECTION_FACTORY);

            context = connectionFactory.createContext(LOGIN, PASSWORD);

            Destination destination = (Destination) namingContext.lookup(DESTINATION);
            log.info("Got JMS Endpoint: {}", DESTINATION);
            context.createProducer().send(destination, event.createMapMessage(context.createMapMessage()));
            log.info("Message: [{}] sent to: {}", env, destination);
        } catch (Exception e) {
            log.error("Error sending message to remote queue.", e);
        } finally {
            try {
                if (namingContext != null) {
                    namingContext.close();
                }
                if (context != null) {
                    context.close();
                }
            } catch (NamingException e) {
                log.warn("Naming exception occurred when closing naming context.");
            }
        }

    }
}
