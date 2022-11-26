package steps;

import org.testng.Assert;
import pages.AuthorizationUserPage;
import pages.SearchCarSalesPage;

public class AuthorizationUserSteps {
    private AuthorizationUserPage authorizationUserPage = new AuthorizationUserPage();

    public void authorizeUser(String login, String password) {
        authorizationUserPage.goToLoginPage().
                enterLogin(login).
                enterPassword(password).
                pressOnLoginButton();
        Assert.assertNotNull(authorizationUserPage.getLoginPageButtonElement());
    }
}
