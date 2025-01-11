package result.agency.result_agency_intern.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client extends BaseEntity {

    private String fullName;
    private String phoneNumber;
    @ManyToOne
    private ServiceName serviceName;
}
