package result.agency.result_agency_intern.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import result.agency.result_agency_intern.entity.enums.RoleName;

@Entity
@Table(name = "_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Role extends BaseEntity implements GrantedAuthority{

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @Override
    public String getAuthority() {
        return roleName.name();
    }
}
