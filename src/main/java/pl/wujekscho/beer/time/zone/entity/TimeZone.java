package pl.wujekscho.beer.time.zone.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "time_zones")
@Getter
@Setter
@ToString
public class TimeZone extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "tzdb_id", nullable = false)
    public String tzdbId;
    @Column(nullable = false)
    public String country;
}
