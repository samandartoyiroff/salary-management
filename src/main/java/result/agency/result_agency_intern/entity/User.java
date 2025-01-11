package result.agency.result_agency_intern.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import result.agency.result_agency_intern.bot.enums.BotState;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@SuperBuilder
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = true, unique = true)
    private String phoneNumber;

    private String password;
    private String username;

    @ManyToOne
    private Role role;

    private Long chatId;

    @Enumerated(EnumType.STRING)
    private BotState state;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    public boolean checkState(BotState botState) {
        return this.state == botState;
    }
}
