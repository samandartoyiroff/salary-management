package result.agency.result_agency_intern.bot;

import com.pengrad.telegrambot.model.request.*;
import org.springframework.stereotype.Service;

@Service
public class BotUtil {
    public Keyboard genrateMenuButtons() {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton(BotConst.HISOBOT),
                new KeyboardButton(BotConst.SOZLAMALAR)
        );
        replyKeyboardMarkup.resizeKeyboard(true);
        return replyKeyboardMarkup;

    }

    public Keyboard reportsCategory() {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton(BotConst.OYLIK_DAROMADLAR),
                new KeyboardButton(BotConst.OYLIK_XARAJATLAR),
                new KeyboardButton(BotConst.QOSHIMCHA_HISOBOTLAR)
        );
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.addRow(
                new KeyboardButton(BotConst.ORQAGA)
        );
        return replyKeyboardMarkup;
    }

    public Keyboard backButton() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(new KeyboardButton(BotConst.ORQAGA));
        replyKeyboardMarkup.resizeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public Keyboard makeSettingButtons() {

        KeyboardButton keyboardButton1 = new KeyboardButton(BotConst.PROFIL_MALUMOTLARINI_OZGARTIRISH);
        KeyboardButton keyboardButton2 = new KeyboardButton(BotConst.KIRISH_HUQUQI);
        KeyboardButton keyboardButton3 = new KeyboardButton(BotConst.PAROLNI_ALMASHTIRISH);
        KeyboardButton keyboardButton4 = new KeyboardButton(BotConst.ORQAGA);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                keyboardButton2, keyboardButton3
        );
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.addRow(keyboardButton1);
        replyKeyboardMarkup.addRow(keyboardButton4);
        return replyKeyboardMarkup;
    }

    public Keyboard declineChangePasswordButton() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton(BotConst.BEKOR_QILISH)
        );
        replyKeyboardMarkup.resizeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public Keyboard changeFullname() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton(BotConst.ISM_FAMILYANI_OZGARTIRISH),
                new KeyboardButton(BotConst.USERNAME_OZGARTIRISH)
        );
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.addRow(new KeyboardButton(BotConst.ORQAGA));
        return replyKeyboardMarkup;

    }
}
