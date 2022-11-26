package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthorizationUserPage extends BasePage{
    @FindBy(css = ".css-1h9spzo.e1k6fwrt0")
    private WebElement loginPageButton;

    @FindBy(id = "sign")
    private WebElement loginInputField;

    @FindBy(id = "password")
    private WebElement passwordInputField;

    @FindBy(id = "signbutton")
    private WebElement loginButton;

    public AuthorizationUserPage goToLoginPage(){
        loginPageButton.click();
        return this;
    }

    public AuthorizationUserPage enterLogin(String login){
        loginInputField.sendKeys(login);
        return this;
    }

    public AuthorizationUserPage enterPassword(String pwd){
        passwordInputField.sendKeys(pwd);
        return this;
    }

    public void pressOnLoginButton(){
        loginButton.click();
    }

    public WebElement getLoginPageButtonElement() {
        return loginPageButton;
    }
}
