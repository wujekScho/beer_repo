package pl.wujekscho.beer.jms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wujekscho.beer.utils.Time;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsageAlertEvent {
    private LocalDateTime timestamp;
    private Long lineBillingUsageId;
    private Long simBillingUsageId;

    @JsonIgnore
    public MapMessage createMapMessage(MapMessage message) throws JMSException {
        if (timestamp != null) {
            message.setLong("timestamp", timestamp.toEpochSecond(ZoneOffset.UTC));
        } else {
            message.setLong("timestamp", Time.nowAtUtc().toEpochSecond(ZoneOffset.UTC));
        }
        if (lineBillingUsageId != null) {
            message.setLong("lineBillingUsageId", lineBillingUsageId);
        }
        if (lineBillingUsageId != null) {
            message.setLong("simBillingUsageId", simBillingUsageId);
        }
        return message;
    }
}