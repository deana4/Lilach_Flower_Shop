package il.client;

import io.github.palexdev.materialfx.controls.MFXTextField;

public class DetailsChecker {

    public int CheckPriceValues(String number, MFXTextField field_to_check){
        //checks the chars (need to contain only digits)
        double val;
        int flag = 0;
        if(field_to_check.getText()==null || field_to_check.getText().equals("")||field_to_check.getText().contains("Error")){
            field_to_check.clear();
            field_to_check.setPromptText("Incorrect");
            flag = 0;
            return flag;
        }
        char[] number_char = number.toCharArray();
        for(int i=0; i<number.length(); i++){
            if(number_char[i] >= '0' && number_char[i] <= '9' || number_char[i] == '.'){
                flag = 1;
            }else{
                flag = 0;
                break;
            }
        }


        if(flag == 1){
            val = Double.valueOf(number);
            if(val > 100 || val <= 0){
                return 0;
            }
        }else if(flag == 0){
            return 0;
        }

        return 1;
    }

}
