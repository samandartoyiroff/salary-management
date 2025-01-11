package result.agency.result_agency_intern.bot;


import result.agency.result_agency_intern.entity.User;

public interface BotService {

    void askUsername(Long chatId);

    void findUserByAndSetChatIdAndAskPassword(String text, Long chatId);

    void checkPasswordAndShowMenu(String text, User currentUser);

    void askUsernameExistUser(Long chatId, User currentUser);

    void showReportCategories(User currentUser);

    void backFromSChhoseReport(User currentUser);

    void sendMonthlyReport(User currentUser);

    void backFromChooseReport(User currentUser);

    void sendMonthlyOutcomeReport(User currentUser);

    void otherReports(User currentUser);

    void showSettingsAndAskOne(User currentUser);

    void backFromShowSettings(User currentUser);

    void changePasswordAndBack(User currentUser);

    void declineChangingPassword(User currentUser);

    void setPasswordAndBack(String text, User currentUser);

    void changeFullName(User currentUser);

    void backFromChangeFullname(User currentUser);

    void askFullNameToChange(User currentUser);

    void changeFullNameAndBack(String text, User currentUser);

    void askUsernameToChange(User currentUser);

    void changeLoginAndBack(String text, User currentUser);

    void showLoginAccess(User currentUser);

    void showLoginAccessAndBack(User currentUser);
}
