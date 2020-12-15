public class HelperMethods {

    public Account getAccountDetails(){
        String email = "s" + "%d" + "@mail.com";
        String username = "srxp" + "%d";
        int num = (int) (Math.random()*10000);
        String improvedPassword = "111111" + num;
        String improvedEmail = String.format(email, num);
        String improvedUsername = String.format(username, num);
        return new Account(improvedEmail,improvedUsername,improvedPassword); //new String[] {improvedEmail, improvedUsername, improvedPassword};
    }


}
