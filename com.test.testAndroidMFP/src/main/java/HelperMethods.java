public class HelperMethods {

    public Account getAccountDetails(){
        String email = "stranger" + "%d" + "@mail.com";
        String username = "stranger" + "%d";
        int num = (int) (Math.random()*1000000);
        String improvedEmail = String.format(email, num);
        String improvedUsername = String.format(username, num);
        String improvedPassword = "1" + num;
        return new Account(improvedEmail, improvedUsername, improvedPassword);
    }

    
}
