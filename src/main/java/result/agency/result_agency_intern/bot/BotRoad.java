package result.agency.result_agency_intern.bot;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import result.agency.result_agency_intern.bot.enums.BotState;
import result.agency.result_agency_intern.entity.User;
import result.agency.result_agency_intern.service.UserService;

import java.util.Optional;

@Service
public class BotRoad {

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private BotService botService;

    @Autowired
    private UserService userService;

    @Async
    public void handleUpdates(Update update) {

        if (update.message()!=null){
            Message message = update.message();
            Long chatId = message.chat().id();
            Optional<User> optionalUser = userService.getUserByChatId(chatId);

            if (message.text()!=null){
                String text = message.text();
                if (optionalUser.isEmpty()) {
                    if (!text.equals("/start")){
                        botService.findUserByAndSetChatIdAndAskPassword(text, chatId);
                    }
                    if (text.equals("/start")) {
                        botService.askUsername(chatId);
                    }
                }

                else {

                    User currentUser = optionalUser.get();

                    if (text.equals("/start")){
                        botService.askUsernameExistUser(chatId, currentUser);
                    }
                    else if(currentUser.checkState(BotState.ASK_USERNAME)){
                        botService.findUserByAndSetChatIdAndAskPassword(text, chatId);
                    }
                    else if (currentUser.checkState(BotState.ASK_PASSWORD)) {
                        botService.checkPasswordAndShowMenu(text, currentUser);
                    }
                    else if (currentUser.checkState(BotState.SHOW_MENU)) {
                        if (text.equals(BotConst.HISOBOT)){
                            botService.showReportCategories(currentUser);
                        } else if (text.equals(BotConst.SOZLAMALAR)) {
                            botService.showSettingsAndAskOne(currentUser);
                        }

                    }
                    else if (currentUser.checkState(BotState.CHOOSE_REPORT)) {
                        if (text.equals(BotConst.ORQAGA)){
                            botService.backFromSChhoseReport(currentUser);
                        }
                        else if (text.equals(BotConst.OYLIK_DAROMADLAR)){
                            botService.sendMonthlyReport(currentUser);
                        }
                        else if (text.equals(BotConst.OYLIK_XARAJATLAR)){
                            botService.sendMonthlyOutcomeReport(currentUser);
                        }
                        else if (text.equals(BotConst.QOSHIMCHA_HISOBOTLAR)){
                            botService.otherReports(currentUser);
                        }
                    }
                    else if (currentUser.checkState(BotState.SEND_DOCUMENT)) {
                        if (text.equals(BotConst.ORQAGA)){
                            botService.backFromChooseReport(currentUser);
                        }
                    }
                    else if (currentUser.checkState(BotState.SHOW_SETTINGS)) {
                        if (text.equals(BotConst.ORQAGA)){
                            botService.backFromShowSettings(currentUser);
                        }
                        else if (text.equals(BotConst.PROFIL_MALUMOTLARINI_OZGARTIRISH)){
                            botService.changeFullName(currentUser);
                        }
                        else if (text.equals(BotConst.KIRISH_HUQUQI)){
                            botService.showLoginAccess(currentUser);
                        }
                        else if (text.equals(BotConst.PAROLNI_ALMASHTIRISH)){
                            botService.changePasswordAndBack(currentUser);
                        }
                    }
                    else if (currentUser.checkState(BotState.CHANGE_PASSWORD)) {

                        if (text.equals(BotConst.BEKOR_QILISH)){
                            botService.declineChangingPassword(currentUser);
                        }
                        else {
                            botService.setPasswordAndBack(text, currentUser);
                        }

                    }
                    else if (currentUser.checkState(BotState.CHANGE_FULLNAME)) {

                        if (text.equals(BotConst.ISM_FAMILYANI_OZGARTIRISH)){
                            botService.askFullNameToChange(currentUser);
                        }
                        else if (text.equals(BotConst.USERNAME_OZGARTIRISH)){
                            botService.askUsernameToChange(currentUser);
                        }
                        else if (text.equals(BotConst.ORQAGA)) {
                            botService.backFromChangeFullname(currentUser);
                        }

                    } else if (currentUser.checkState(BotState.ACCEPT_FULLNAME)) {
                        botService.changeFullNameAndBack(text, currentUser);
                    }
                    else if (currentUser.checkState(BotState.ACCEPT_LOGIN)) {
                        botService.changeLoginAndBack(text, currentUser);
                    }
                    else if (currentUser.checkState(BotState.SHOW_LOGIN_ACCESS)){
                        if (text.equals(BotConst.ORQAGA)){
                            botService.showLoginAccessAndBack(currentUser);
                        }
                    }


                }

            }



        }
        else if (update.callbackQuery()!=null) {


        }


    }
}
