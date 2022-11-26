package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import steps.AuthorizationUserSteps;
import steps.PersonalAccountSteps;

public class PersonalAccountTest extends BaseTest {
    private AuthorizationUserSteps authorizationSteps = new AuthorizationUserSteps();
    private PersonalAccountSteps personalAccountSteps = new PersonalAccountSteps();

    @BeforeTest
    public void authorizeUser() {
        String login = "your_login";
        String password = "your_pwd";

        authorizationSteps.authorizeUser(login, password);
        personalAccountSteps.clearFavourites();
    }

    @Test
    public void addCarAdToFavourites() {
        personalAccountSteps.addCarAdToFavourites().
                verifyThatCarAdWereAddedToFavourites();
    }

}
