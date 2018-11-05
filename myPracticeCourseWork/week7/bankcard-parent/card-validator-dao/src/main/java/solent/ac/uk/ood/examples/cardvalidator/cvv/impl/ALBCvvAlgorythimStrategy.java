/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.cvv.impl;

import solent.ac.uk.ood.examples.cardvalidator.model.CreditCard;
import solent.ac.uk.ood.examples.cardvalidator.model.CvvAlgorythimStrategy;

/**
 *
 * @author 3lelia74
 */
public class ALBCvvAlgorythimStrategy implements CvvAlgorythimStrategy{
    
    private final String CVV = "111";
    @Override
    public CreditCard addCvv(CreditCard card) {
        card.setCvv(CVV);
        return card;
    }

    @Override
    public boolean checkCvv(CreditCard card) {
        return card.getCvv().equals(CVV);
    }
    
}
