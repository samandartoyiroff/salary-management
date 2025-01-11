package result.agency.result_agency_intern.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import result.agency.result_agency_intern.bot.enums.BotState;
import result.agency.result_agency_intern.entity.User;
import result.agency.result_agency_intern.repository.UserRepository;
import result.agency.result_agency_intern.util.ReportService;

import java.io.File;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BotServiceImpl implements BotService {

    private final TelegramBot myBot;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BotUtil botUtil;
    private final ReportService reportService;


    @Override
    public void askUsername(Long chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "Loginingizni (Username) kiriting");
        myBot.execute(sendMessage);
    }

    @Override
    public void findUserByAndSetChatIdAndAskPassword(String username, Long chatId) {

        Optional<User> optionalUser = userRepository.findByUsernameAndDeletedFalse(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setChatId(chatId);
            userRepository.save(user);
            SendMessage sendMessage = new SendMessage(chatId, "Parolingizni kiriting");
            myBot.execute(sendMessage);
            user.setState(BotState.ASK_PASSWORD);
            userRepository.save(user);
        }
        else {
            SendMessage sendMessage = new SendMessage(chatId, "Kechiradi siz bizning xodim emassiz." +
                    " Shuning uchun bu botdan foydalana olmaysiz");
            myBot.execute(sendMessage);
        }

    }

    @Override
    public void checkPasswordAndShowMenu(String text, User currentUser) {

        if (passwordEncoder.matches(text, currentUser.getPassword())) {
            SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Menu");
            sendMessage.replyMarkup(botUtil.genrateMenuButtons());
            myBot.execute(sendMessage);
            currentUser.setState(BotState.SHOW_MENU);
            userRepository.save(currentUser);
        }
        else {
            SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Parol xato qaytadan kiriting");
            myBot.execute(sendMessage);
        }


    }

    @Override
    public void askUsernameExistUser(Long chatId, User currentUser) {

        SendMessage sendMessage = new SendMessage(chatId, "Loginingizni (Username) kiriting");
        currentUser.setState(BotState.ASK_USERNAME);
        userRepository.save(currentUser);
        myBot.execute(sendMessage);

    }

    @Override
    public void showReportCategories(User currentUser) {

        SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Hisobotlardan birini tanlang");
        sendMessage.replyMarkup(botUtil.reportsCategory());
        currentUser.setState(BotState.CHOOSE_REPORT);
        userRepository.save(currentUser);
        myBot.execute(sendMessage);

    }

    @Override
    public void backFromSChhoseReport(User currentUser) {
        SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Menu");
        sendMessage.replyMarkup(botUtil.genrateMenuButtons());
        myBot.execute(sendMessage);
        currentUser.setState(BotState.SHOW_MENU);
        userRepository.save(currentUser);
    }

    @Override
    public void sendMonthlyReport(User currentUser)  {
        try {
            File monthlyReport = reportService.genereteMonthlyIncomeReport();
            SendDocument sendDocument = new SendDocument(currentUser.getChatId(), monthlyReport);
            sendDocument.replyMarkup(botUtil.backButton());
            currentUser.setState(BotState.SEND_DOCUMENT);
            userRepository.save(currentUser);
            myBot.execute(sendDocument);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void backFromChooseReport(User currentUser) {
        SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Hisobotlardan birini tanlang");
        sendMessage.replyMarkup(botUtil.reportsCategory());
        currentUser.setState(BotState.CHOOSE_REPORT);
        userRepository.save(currentUser);
        myBot.execute(sendMessage);

    }

    @Override
    public void sendMonthlyOutcomeReport(User currentUser) {
        try {
            File monthlyReport = reportService.genereteMonthlyOutcomeReport();
            SendDocument sendDocument = new SendDocument(currentUser.getChatId(), monthlyReport);
            sendDocument.replyMarkup(botUtil.backButton());
            currentUser.setState(BotState.SEND_DOCUMENT);
            userRepository.save(currentUser);
            myBot.execute(sendDocument);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void otherReports(User currentUser) {
    try {

        File file = reportService.generateByTransactionReport();
        SendDocument sendDocument = new SendDocument(currentUser.getChatId(), file);
        userRepository.save(currentUser);
        myBot.execute(sendDocument);

        File file1 = reportService.generateByPayType();
        SendDocument sendDocument1 = new SendDocument(currentUser.getChatId(), file1);
        sendDocument1.replyMarkup(botUtil.backButton());
        currentUser.setState(BotState.SEND_DOCUMENT);
        myBot.execute(sendDocument1);
    }
    catch (Exception e) {
        e.printStackTrace();
    }

    }

    @Override
    public void showSettingsAndAskOne(User currentUser) {

        SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Birini tanlang");
        sendMessage.replyMarkup(botUtil.makeSettingButtons());
        myBot.execute(sendMessage);
        currentUser.setState(BotState.SHOW_SETTINGS);
        userRepository.save(currentUser);
    }

    @Override
    public void backFromShowSettings(User currentUser) {
        SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Menu");
        sendMessage.replyMarkup(botUtil.genrateMenuButtons());
        myBot.execute(sendMessage);
        currentUser.setState(BotState.SHOW_MENU);
        userRepository.save(currentUser);
    }

    @Override
    public void changePasswordAndBack(User currentUser) {

        SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Yangi parolni kiriting");
        sendMessage.replyMarkup(botUtil.declineChangePasswordButton());
        currentUser.setState(BotState.CHANGE_PASSWORD);
        userRepository.save(currentUser);
        myBot.execute(sendMessage);

    }

    @Override
    public void declineChangingPassword(User currentUser) {
        SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Birini tanlang");
        sendMessage.replyMarkup(botUtil.makeSettingButtons());
        myBot.execute(sendMessage);
        currentUser.setState(BotState.SHOW_SETTINGS);
        userRepository.save(currentUser);

    }

    @Override
    public void setPasswordAndBack(String text, User currentUser) {

        if (text.length()>=8){

            String encode = passwordEncoder.encode(text);
            currentUser.setPassword(encode);
            userRepository.save(currentUser);
            SendMessage sendMessage1 = new SendMessage(currentUser.getChatId(), "Parol o'zgardi");
            myBot.execute(sendMessage1);
            SendMessage sendMessage2 = new SendMessage(currentUser.getChatId(), "Birini tanlang");
            sendMessage2.replyMarkup(botUtil.makeSettingButtons());
            myBot.execute(sendMessage2);
            currentUser.setState(BotState.SHOW_SETTINGS);
            userRepository.save(currentUser);
        }
        else {

            SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Parol kamida 8 ta belgi bo'lishi kerak." +
                    " Qaytadan urinib ko'ring");
            myBot.execute(sendMessage);
        }
    }

    @Override
    public void changeFullName(User currentUser) {

        SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Tanlang");
        sendMessage.replyMarkup(botUtil.changeFullname());
        currentUser.setState(BotState.CHANGE_FULLNAME);
        userRepository.save(currentUser);
        myBot.execute(sendMessage);

    }

    @Override
    public void backFromChangeFullname(User currentUser) {
        SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Birini tanlang");
        sendMessage.replyMarkup(botUtil.makeSettingButtons());
        myBot.execute(sendMessage);
        currentUser.setState(BotState.SHOW_SETTINGS);
        userRepository.save(currentUser);
    }

    @Override
    public void askFullNameToChange(User currentUser) {
        SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Ism-familyangizni kiriting");
        currentUser.setState(BotState.ACCEPT_FULLNAME);
        userRepository.save(currentUser);
        myBot.execute(sendMessage);
    }

    @Override
    public void changeFullNameAndBack(String fullName, User currentUser) {
        currentUser.setFullName(fullName);
        userRepository.save(currentUser);
        SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Ism-familyangiz o'zgardi");
        myBot.execute(sendMessage);
        backFromChangeFullname(currentUser);
    }

    @Override
    public void askUsernameToChange(User currentUser) {

        SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Yangi loginni (usernameni) kiriting");
        currentUser.setState(BotState.ACCEPT_LOGIN);
        userRepository.save(currentUser);
        myBot.execute(sendMessage);
    }

    @Override
    public void changeLoginAndBack(String login, User currentUser) {

        if (login.equals(currentUser.getUsername())){
            SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Login o'zgardi");
            myBot.execute(sendMessage);
            backFromChangeFullname(currentUser);
        }
        else {

            Optional<User> optionalUser = userRepository.findByUsernameAndDeletedFalse(login);
            if (optionalUser.isPresent()){
                SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Login xato. Bu username boshqa foydalanuvchiga tegishli" +
                        "Qaytadan urinib ko'ring");
                myBot.execute(sendMessage);
            }
            else {
                currentUser.setUsername(login);
                userRepository.save(currentUser);
                SendMessage sendMessage = new SendMessage(currentUser.getChatId(), "Login o'zgardi");
                myBot.execute(sendMessage);
                backFromChangeFullname(currentUser);
            }

        }
    }

    @Override
    public void showLoginAccess(User currentUser) {
        SendMessage sendMessage = new SendMessage(currentUser.getChatId(), """
                Login: %s,
                Ism-familiya: %s
                Parol: ******** (ko'rsatilmaydi)
                
                """.formatted(currentUser.getUsername(), currentUser.getFullName()));
        sendMessage.replyMarkup(botUtil.backButton());
        currentUser.setState(BotState.SHOW_LOGIN_ACCESS);
        userRepository.save(currentUser);
        myBot.execute(sendMessage);
    }

    @Override
    public void showLoginAccessAndBack(User currentUser) {
        backFromChangeFullname(currentUser);
    }
}
