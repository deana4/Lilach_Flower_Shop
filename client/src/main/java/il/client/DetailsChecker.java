package il.client;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;

public class DetailsChecker {

    public int CheckPriceValues(String number, String PriceOrPercent){
        //checks the chars (need to contain only digits)
        double val;
        int flag = 0;

        char[] number_char = number.toCharArray();
        for(int i=0; i<number.length(); i++){
            if(number_char[i] >= '0' && number_char[i] <= '9' || number_char[i] == '.'){
                flag = 1;
            }else{
                flag = 0;
                break;
            }
        }

        if(PriceOrPercent.equals("percent")) {
            if (flag == 1) {
                val = Double.valueOf(number);
                if (val > 100 || val <= 0) {
                    return 0;
                }
            } else if (flag == 0) {
                return 0;
            }
        }else{
            return flag;
        }

        return 1;
    }

    public int creditCardCheck (String creditCard){
        //checks the length (need to be 16 exactly) and chars (need to contain only digits)
        if(creditCard.length()!=16){
            return 0;
        }
        char[] credit_card = creditCard.toCharArray();
        for(int i=0; i<creditCard.length(); i++){
            if(credit_card[i]<'0' || credit_card[i]>'9'){
                return 0;
            }
        }
        return 1;
    }

    public int phoneCheck (String phone) {
        //checks if kidumet is 050/2/3/4
        //checks the length (need to be 7 exactly) and chars (need to contain only digits)
        if (phone.length() != 10) {
            return 0;
        } else {
            char[] phone_number = phone.toCharArray();

            if(phone_number[0]!='0' || phone_number[1]!='5' || (phone_number[2]!='0' && phone_number[2]!='2' && phone_number[2]!='3' && phone_number[2]!='4')){
                return 0;
            }

            for (int i = 3; i < phone.length(); i++) {
                if (phone_number[i] < '0' || phone_number[i] > '9') {
                    return 0;
                }
            }
        }
        return 1;
    }

    public int mailCheck (String mail){
        //check @ in the mail and that the before and after the @ (till the dot) are at least with 2 chars
        //check that there is a dot after the @
        //mail can contain only A-z/digits
        if(!mail.contains("@") || !mail.contains(".")){
            return 0;
        }
        int at_index = mail.indexOf("@");
        if(at_index<=1 || at_index>=(mail.length()-2)){
            return 0;
        }
        int dot_index = mail.indexOf(".");
        if(dot_index - at_index < 2){
            return 0;
        }
        if(dot_index>=(mail.length()-2)){
            return 0;
        }
        char[] mail_char = mail.toCharArray();
        //before the @
        for(int i=0; i<at_index; i++){
            if((mail_char[i]<'A' || mail_char[i]>'Z') && (mail_char[i]<'a' || mail_char[i]>'z') && (mail_char[i] < '0' || mail_char[i] > '9')){
                return 0;
            }
        }
        //after the @
        for(int i=at_index+1; i<mail_char.length; i++){
            if((mail_char[i]<'A' || mail_char[i]>'Z') && (mail_char[i]<'a' || mail_char[i]>'z') && (mail_char[i] != '.')){
                return 0;
            }
        }
        return 1;
    }

    public int passwordCheck(String password){
        //needs to check length (bigger then 8), chars (A-z0-9),
        if(password.length()<8){
            return 0;
        }
        char[] pass = password.toCharArray();
        for (int i=0; i<password.length(); i++){
            if((pass[i]<'A' || pass[i]>'Z') && (pass[i]<'a' || pass[i]>'z') && (pass[i]<'0' || pass[i]>'9')){
                return 0;
            }
        }
        return 1;
    }

    public int permissionCheck (String permission, String userOrWorker){
        if(userOrWorker.equals("user")) {
            if (!permission.equals("1") && !permission.equals("2") && !permission.equals("3")) {
                return 0;
            }
            else{
                return 1;
            }
        } else if(userOrWorker.equals("worker")){
            if (!permission.equals("1") && !permission.equals("2") && !permission.equals("3") && !permission.equals("4") && !permission.equals("5")) {
                return 0;
            }
            else{
                return 1;
            }
        }
        return -1; //we dont need to be here
    }

    public int EmptyCheck (String string){
        if(string.equals("")||string.equals(" ")||string.equals("Empty")){
            return 0;
        } else{
            return 1;
        }
    }



}
