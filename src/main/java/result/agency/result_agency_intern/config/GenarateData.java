package result.agency.result_agency_intern.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import result.agency.result_agency_intern.entity.OutcomeName;
import result.agency.result_agency_intern.entity.Role;
import result.agency.result_agency_intern.entity.ServiceName;
import result.agency.result_agency_intern.entity.User;
import result.agency.result_agency_intern.entity.enums.RoleName;
import result.agency.result_agency_intern.repository.OutcomeNameRepository;
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
    @Autowired
    private OutcomeNameRepository outcomeNameRepository;

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


            OutcomeName outcomeName1 = OutcomeName.builder()
                    .name("Oylik")
                    .build();
            OutcomeName outcomeName2 = OutcomeName.builder()
                    .name("Divedend")
                    .build();
            OutcomeName outcomeName3 = OutcomeName.builder()
                    .name("Reklama")
                    .build();
            OutcomeName outcomeName4 = OutcomeName.builder()
                    .name("Mijoz reklamasi")
                    .build();
            OutcomeName outcomeName5 = OutcomeName.builder()
                    .name("Taraqqiyot jamg'armasi")
                    .build();
            OutcomeName outcomeName6 = OutcomeName.builder()
                    .name("Fiskal xarajatlar")
                    .build();
            OutcomeName outcomeName7 = OutcomeName.builder()
                    .name("Maishiy xarajatlar")
                    .build();
            OutcomeName outcomeName8 = OutcomeName.builder()
                    .name("Xizmatlar")
                    .build();
            OutcomeName outcomeName9 = OutcomeName.builder()
                    .name("Kasaba uyishmasi")
                    .build();
            OutcomeName outcomeName10 = OutcomeName.builder()
                    .name("Komissiya haqqi")
                    .build();
             OutcomeName outcomeName11 = OutcomeName.builder()
                    .name("Boshqa")
                    .build();

             outcomeNameRepository.save(outcomeName1);
             outcomeNameRepository.save(outcomeName2);
             outcomeNameRepository.save(outcomeName3);
             outcomeNameRepository.save(outcomeName4);
             outcomeNameRepository.save(outcomeName5);
             outcomeNameRepository.save(outcomeName6);
             outcomeNameRepository.save(outcomeName7);
             outcomeNameRepository.save(outcomeName8);
             outcomeNameRepository.save(outcomeName9);
             outcomeNameRepository.save(outcomeName10);
             outcomeNameRepository.save(outcomeName11);




        }

    }
}
