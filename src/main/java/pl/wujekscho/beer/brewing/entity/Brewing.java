package pl.wujekscho.beer.brewing.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wujekscho.beer.security.entity.User;
import pl.wujekscho.beer.utils.Time;
import pl.wujekscho.beer.yeast.entity.Yeast;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brewings")
public class Brewing extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(unique = true, nullable = false)
    public String name;
    @ManyToOne
    public Yeast yeast;
    @ManyToOne
    public User user;
    @Column(nullable = false)
    public LocalDateTime created;
    @Column(nullable = false)
    public String style;
    @Column(nullable = false)
    public Double gravity;
    @Column(nullable = false)
    public Double volume;

    @PrePersist
    public void prePersist() {
        this.created = Time.nowAtUtc();
    }
}