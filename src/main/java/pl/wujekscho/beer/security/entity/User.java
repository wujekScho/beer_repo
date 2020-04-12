package pl.wujekscho.beer.security.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.wujekscho.beer.time.zone.entity.TimeZone;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Email
    @NotBlank
    public String login;
    @NotBlank
    public String password;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "role")
    public Set<Role> roles;
    public boolean activated;
    @ManyToOne
    @JoinColumn(name = "time_zone_id", referencedColumnName = "id")
    public TimeZone timeZone;
}
