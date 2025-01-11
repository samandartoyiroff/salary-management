package result.agency.result_agency_intern.config;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import result.agency.result_agency_intern.entity.*;
import result.agency.result_agency_intern.entity.enums.PayType;
import result.agency.result_agency_intern.entity.enums.RoleName;
import result.agency.result_agency_intern.entity.enums.TransactionCondition;
import result.agency.result_agency_intern.entity.enums.TransactionType;
import result.agency.result_agency_intern.repository.*;

import java.util.List;
import java.util.Random;

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
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TransactionRepository transactionRepository;

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
                    .phoneNumber("912392304")
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

             User user = User.builder()
                     .role(role1)
                     .username("user")
                     .password(passwordEncoder.encode("1234"))
                     .fullName("Ali Valiyev")
                     .phoneNumber("912392309")
                     .build();
             userRepository.save(user);

            Faker faker = new Faker();
            List<ServiceName> serviceNames = serviceNameRepository.findAllByDeletedFalse();
            Random random = new Random();

            for (int i = 0; i < 10; i++) {

                Client client = Client.builder()
                        .fullName(faker.name().fullName())
                        .phoneNumber(faker.phoneNumber().phoneNumber())
                        .serviceName(serviceNames.get( random.nextInt(0,serviceNames.size()-1) ))
                        .build();
                clientRepository.save(client);

            }

            List<Client> clients = clientRepository.findAllByDeletedFalse();

            Transaction transaction1 = Transaction.builder()
                    .amount(123330.33)
                    .description("ien ein")
                    .payType(PayType.CASH_CURRENCY)
                    .transactionType(TransactionType.KOCHIRISH)
                    .build();
            transactionRepository.save(transaction1);

            Transaction transaction2 = Transaction.builder()
                    .amount(122323.23)
                    .description("ien ein")
                    .payType(PayType.CARD)
                    .transactionType(TransactionType.KOCHIRISH)
                    .build();
            transactionRepository.save(transaction2);

            Transaction transaction3 = Transaction.builder()
                    .amount(122323.23)
                    .description("tolov")
                    .payType(PayType.CARD)
                    .client(clients.get(4))
                    .transactionCondition(TransactionCondition.MOLIYAVIY_QARZ)
                    .transactionType(TransactionType.DAROMAD)
                    .build();
            transactionRepository.save(transaction3);


            Transaction transaction4 = Transaction.builder()
                    .amount(342323.23)
                    .description("tolov")
                    .payType(PayType.CARD)
                    .client(clients.get(3))
                    .transactionCondition(TransactionCondition.REJALASHTIRILGAN)
                    .transactionType(TransactionType.DAROMAD)
                    .build();
            transactionRepository.save(transaction4);


            Transaction transaction5 = Transaction.builder()
                    .amount(109323.23)
                    .description("tolov")
                    .payType(PayType.BANK_TRANSFER)
                    .client(clients.get(6))
                    .transactionCondition(TransactionCondition.KEYIN_TOLOV)
                    .transactionType(TransactionType.DAROMAD)
                    .build();
            transactionRepository.save(transaction5);



            Transaction transaction6 = Transaction.builder()
                    .amount(900330.33)
                    .description("ien ein")
                    .payType(PayType.CASH_CURRENCY)
                    .transactionType(TransactionType.XARAJAT)
                    .outcomeName(outcomeName7)
                    .build();
            transactionRepository.save(transaction6);

            Transaction transaction7 = Transaction.builder()
                    .amount(1900330.33)
                    .description("fmoien vunwe weuenf")
                    .payType(PayType.CASH)
                    .transactionType(TransactionType.XARAJAT)
                    .outcomeName(outcomeName3)
                    .build();
            transactionRepository.save(transaction7);

        }

    }
}
