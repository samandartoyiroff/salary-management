package result.agency.result_agency_intern.bot;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BotRoad {

    @Autowired
    private TelegramBot telegramBot;

    @Async
    public void handleUpdates(Update update) {

        if (update.message()!=null){
            Message message = update.message();
            Long chatId = message.chat().id();
            if (message.text().equals("/start")) {
                SendMessage sendMessage = new SendMessage(chatId, "Ishladi");
                telegramBot.execute(sendMessage);
            }
        }
        else if (update.callbackQuery()!=null) {


        }


    }
}
