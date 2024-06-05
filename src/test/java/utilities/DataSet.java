package utilities;

import org.testng.annotations.DataProvider;

public class DataSet {
    @DataProvider(name = "invalidCredentials")
    public static Object invalidCredentials(){
        Object[][] dataset = {{"ttt@gmail.com","tttttt","Unknown email address. Check again or try your username."},
                {"kisivel401@adrais.com","tttt","Error: The password you entered for the email address kisivel401@adrais.com is incorrect. Lost your password?"},
                {"kisiv401@adris.com","zxcvqwer","Unknown email address. Check again or try your username."},
                {"","123456","Error: ইউজার নাম প্রয়োজন"},
                {"kisivel401@adrais.com","","Error: The password field is empty."}};
        return dataset;
    }
}
