package result.agency.result_agency_intern.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import result.agency.result_agency_intern.entity.Role;
import result.agency.result_agency_intern.entity.ServiceName;
import result.agency.result_agency_intern.entity.User;
import result.agency.result_agency_intern.entity.enums.RoleName;
import result.agency.result_agency_intern.repository.RoleRepository;
import result.agency.result_agency_intern.repository.ServiceNameRepository;
import result.agency.result_agency_intern.repository.UserRepository;

@Service
public class GenarateData implements CommandLineRunner {

    @Autowired
    private ServiceNameRepository serviceNameRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String isCreate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        if (isCreate.equals("create")) {

            ServiceName serviceName1 = ServiceName.builder()
                    .name("Veb sayt yaratish")
                    .build();

            ServiceName serviceName2 = ServiceName.builder()
                    .name("Bot ishlab chiqish")
                    .build();

            ServiceName serviceName3 = ServiceName.builder()
                    .name("SMM xizmati")
                    .build();

            ServiceName serviceName4 = ServiceName.builder()
                    .name("Kontekst reklamasini ishga tushirish")
                    .build();

            ServiceName serviceName5 = ServiceName.builder()
                    .name("Target reklamasini ishga tushirish")
                    .build();

            ServiceName serviceName6 = ServiceName.builder()
                    .name("Brending")
                    .build();

            ServiceName serviceName7 = ServiceName.builder()
                    .name("SEO")
                    .build();

            ServiceName serviceName8 = ServiceName.builder()
                    .name("Boshqa")
                    .build();

            serviceNameRepository.save(serviceName1);
            serviceNameRepository.save(serviceName2);
            serviceNameRepository.save(serviceName3);
            serviceNameRepository.save(serviceName4);
            serviceNameRepository.save(serviceName5);
            serviceNameRepository.save(serviceName6);
            serviceNameRepository.save(serviceName7);
            serviceNameRepository.save(serviceName8);

            Role role1 = Role.builder()
                    .roleName(RoleName.ROLE_USER)
                    .build();
            Role role2 = Role.builder()
                    .roleName(RoleName.ROLE_ADMIN)
                    .build();
            Role role3 = Role.builder()
                    .roleName(RoleName.ROLE_SUPERADMIN)
                    .build();

            roleRepository.save(role1);
            roleRepository.save(role2);
            roleRepository.save(role3);

            User admin = User.builder()
                    .role(role2)
                    .username("admin")
                    .password(passwordEncoder.encode("1234"))
                    .fullName("Ali Valiyev")
                    .phoneNumber("912392309")
                    .build();

            userRepository.save(admin);

        }

    }
}
