/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.cvv.impl.test;

import org.junit.Test;
import static org.junit.Assert.*;
import solent.ac.uk.ood.examples.cardvalidator.cvv.impl.TrivialCvvAlgorythimStrategy;
import solent.ac.uk.ood.examples.cardvalidator.impl.CardFactoryDAOImpl;
import solent.ac.uk.ood.examples.cardvalidator.impl.CreditCardFactoryAndValidatorImpl;
import solent.ac.uk.ood.examples.cardvalidator.impl.SupportedCardIssuerIdentificationNumbers;
import solent.ac.uk.ood.examples.cardvalidator.model.CardFactoryDAO;
import solent.ac.uk.ood.examples.cardvalidator.model.CardOrganisation;
import solent.ac.uk.ood.examples.cardvalidator.model.CreditCard;
import solent.ac.uk.ood.examples.cardvalidator.model.CreditCardFactoryAndValidator;
import solent.ac.uk.ood.examples.cardvalidator.model.CvvAlgorythimStrategy;
import solent.ac.uk.ood.examples.cardvalidator.cvv.impl.VNWCvvAlgorythimStrategy;
import solent.ac.uk.ood.examples.cardvalidator.cvv.impl.ALBCvvAlgorythimStrategy;

/**
 *
 * @author cgallen
 */
public class CreditCardFactoryAndValidatorImplTest {

    @Test
    public void createAndVerifyCardTest() {

        CreditCardFactoryAndValidator ccFactoryandValidator = new CreditCardFactoryAndValidatorImpl();

        // set trivial algorythm
        CvvAlgorythimStrategy cvvAlgorythimStrategy = new TrivialCvvAlgorythimStrategy();
        ccFactoryandValidator.setCvvAlgorythim(cvvAlgorythimStrategy);

        // set provider
        String issuerIdentificationNumber = SupportedCardIssuerIdentificationNumbers.VISA_NAT_WEST;
        ccFactoryandValidator.setIssuerIdentificationNumber(issuerIdentificationNumber);

        String accountNumber = "123456789"; // 9 digits + iin + 1 = 16
        String name = "Fred Blogs";
        String endDate = "0119";
        String issueNumber = "01";
        CreditCard newCreditcard = ccFactoryandValidator.createCreditCard(accountNumber, name, endDate, issueNumber);

        System.out.println(" new creditcard created:" + newCreditcard);

        boolean cvvIsValid = ccFactoryandValidator.cvvIsValid(newCreditcard);
        assertTrue(cvvIsValid);
        System.out.println(" cvv:" + newCreditcard.getCvv());
        
        boolean lunnIsValid = ccFactoryandValidator.cardNumberLunnIsValid(newCreditcard);
        assertTrue(lunnIsValid);
        
    }

    @Test
    public void findCreditCardSystemTest() {

        // create a new valid creditcard
        // note using iin SupportedCardIssuerIdentificationNumbers.VISA_NAT_WEST
        String cardnumber = "4285" + "8612" + "3456" + "7892"; // 9 digits + iin + 1 = 16
        String cvv = "000";
        String name = "Fred Blogs";
        String endDate = "0119";
        String issueNumber = "01";

        CreditCard newCreditcard = new CreditCard();
        newCreditcard.setCardnumber(cardnumber);
        newCreditcard.setCvv(cvv);
        newCreditcard.setEndDate(endDate);
        newCreditcard.setIssueNumber(issueNumber);
        newCreditcard.setName(name);

        System.out.println(" new creditcard created:" + newCreditcard);

        CreditCardFactoryAndValidator ccFactoryandValidator = new CreditCardFactoryAndValidatorImpl();
        // set trivial algorythm (not actually used)
        CvvAlgorythimStrategy cvvAlgorythimStrategy = new TrivialCvvAlgorythimStrategy();
        ccFactoryandValidator.setCvvAlgorythim(cvvAlgorythimStrategy);

        CardOrganisation cardOrganisation = ccFactoryandValidator.getCardOrganisation(newCreditcard);

        System.out.println(" cardOrganisation:" + cardOrganisation);

        assertEquals(CardOrganisation.VISA, cardOrganisation);

    }
    
    @Test
    public void createAndVerifyVNWCardTest() {

        CreditCardFactoryAndValidator ccFactoryandValidator = new CreditCardFactoryAndValidatorImpl();

        // set trivial algorythm
        CvvAlgorythimStrategy cvvAlgorythimStrategy = new VNWCvvAlgorythimStrategy();
        ccFactoryandValidator.setCvvAlgorythim(cvvAlgorythimStrategy);

        // set provider
        String issuerIdentificationNumber = SupportedCardIssuerIdentificationNumbers.VISA_NAT_WEST;
        ccFactoryandValidator.setIssuerIdentificationNumber(issuerIdentificationNumber);

        String accountNumber = "123456789"; // 9 digits + iin + 1 = 16
        String name = "Fred Blogs";
        String endDate = "0119";
        String issueNumber = "01";
        CreditCard newCreditcard = ccFactoryandValidator.createCreditCard(accountNumber, name, endDate, issueNumber);

        System.out.println(" new creditcard created:" + newCreditcard);

        boolean cvvIsValid = ccFactoryandValidator.cvvIsValid(newCreditcard);
        assertTrue(cvvIsValid);
        System.out.println(" cvv:" + newCreditcard.getCvv());
        
        boolean lunnIsValid = ccFactoryandValidator.cardNumberLunnIsValid(newCreditcard);
        assertTrue(lunnIsValid);
        
    }
}

