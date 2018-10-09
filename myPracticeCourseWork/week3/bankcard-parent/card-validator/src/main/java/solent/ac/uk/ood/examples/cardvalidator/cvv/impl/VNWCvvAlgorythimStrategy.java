/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.cvv.impl;

import solent.ac.uk.ood.examples.cardvalidator.model.CreditCard;
import solent.ac.uk.ood.examples.cardvalidator.model.CvvAlgorythimStrategy;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author 3lelia74
 */
public class VNWCvvAlgorythimStrategy implements CvvAlgorythimStrategy {

    @Override
    public CreditCard addCvv(CreditCard card) {
        int sum=0;
        
        //Card Number calculations
        String cardNum=card.getCardnumber();
        List<String> cardNumArr = new ArrayList<String>();
        for(int i = 0; i < cardNum.length(); i++){
            cardNumArr.add(cardNum.substring(i, i+1));
        }
        for (String dig: cardNumArr) {
            sum=sum+Integer.parseInt(dig);
        }
        //Name calculations
        String name=card.getName();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (!t.isEmpty()) {
                t += " ";
            }
            int n = (int)ch - (int)'a' + 1;
            t += String.valueOf(n);
        }
        //Results
        String cvvRes="000";
        if(sum>999){
            sum=sum-1000;
            cvvRes=Integer.toString(sum);
        } else if(sum<100){
            cvvRes="0"+Integer.toString(sum);
        } else if(sum<10){
            cvvRes="00"+Integer.toString(sum);
        }
        
        card.setCvv(cvvRes);
        return card;
    }

    @Override
    public boolean checkCvv(CreditCard card) {
        CreditCard cardCheck=card;
        cardCheck=addCvv(cardCheck);
        if(cardCheck.getCvv().equals(card.getCvv())){
            return true;
        }else{
            return false;
        }
        
    }
    
}
