package result.agency.result_agency_intern.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MyBot implements CommandLineRunner {

    @Autowired
    private  TelegramBot myBot;
    @Autowired
    private  BotRoad botRoad;

    @Override
    public void run(String... args) throws Exception {

        myBot.setUpdatesListener((UpdatesListener) list -> {

            for (Update update : list) {
                botRoad.handleUpdates(update);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }
}
