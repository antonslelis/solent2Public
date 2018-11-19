/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.cardservice;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.cardvalidator.model.Account;
import solent.ac.uk.ood.examples.cardvalidator.model.AccountDAO;
import solent.ac.uk.ood.examples.cardvalidator.model.BankApi;
import solent.ac.uk.ood.examples.cardvalidator.model.CardFactoryDAO;
import solent.ac.uk.ood.examples.cardvalidator.model.CreditCard;

/**
 *
 * @author cgallen
 */
public class BankApiImpl implements BankApi {

    private static final Logger LOG = LoggerFactory.getLogger(BankApiImpl.class);
    
    private static final Logger TRANSACTIONLOG = LoggerFactory.getLogger("transaction-log");

    private final CardFactoryDAO cardFactoryDao;

    private final AccountDAO accountDAO;

    public BankApiImpl(CardFactoryDAO cardFactoryDao, AccountDAO accountDAO) {
        this.cardFactoryDao = cardFactoryDao;
        this.accountDAO = accountDAO;
    }

    @Override
    public Account createAccount(String issuerIdentificationNumber, String name) {
        Account newAcc=accountDAO.createAccount(issuerIdentificationNumber,name);
        LOG.debug("New account: "+newAcc);
        return newAcc;
    }

    @Override
    public boolean deleteAccount(String issuerIdentificationNumber, String individualAccountIdentifier) {
        boolean delAccRes=accountDAO.deleteAccount(issuerIdentificationNumber, individualAccountIdentifier);
        if(delAccRes){
            LOG.debug("The account "+individualAccountIdentifier+" from the bank "+issuerIdentificationNumber+" has been deleted");
        }else{
            LOG.debug("The account with ids("+issuerIdentificationNumber+" and "+individualAccountIdentifier+") doesn't exist");
        }
        return delAccRes;
    }

    @Override
    public Account retrieveAccount(String issuerIdentificationNumber, String individualAccountIdentifier) {
        Account newAcc=accountDAO.retrieveAccount(issuerIdentificationNumber, individualAccountIdentifier);
        if(newAcc==null){
            LOG.debug("The account with ids("+issuerIdentificationNumber+" and "+individualAccountIdentifier+") doesn't exist");
        }else{
            LOG.debug("Account: "+newAcc);
        }
        return newAcc;
    }

    @Override
    public Account updateAccount(Account account) {
        Account newAcc=accountDAO.updateAccount(account);
        if(newAcc==null){
            LOG.debug("Nothing has been updated");
        }else{
            LOG.debug("The account has been updated. New account: "+newAcc);
        }
        return newAcc;
    }

    @Override
    public List<Account> getAccountsForIssuer(String issuerIdentificationNumber) {
       List<Account> newAccList=accountDAO.getAccountsForIssuer(issuerIdentificationNumber);
       if(newAccList==null){
           LOG.debug("Accounts with issuer identification number "+issuerIdentificationNumber+" don't exist");
       }else{
           LOG.debug("Retrived list of accounts: "+newAccList);
       }
       return newAccList;
    }

    @Override
    public List<String> getSupportedIssuerNames() {
        return cardFactoryDao.getSupportedIssuerNames();
    }

    @Override
    public String getIssuerIdentifierNumberForName(String name) {
        return cardFactoryDao.getIssuerIdentifierNumberForName(name);
    }

    @Override
    public String getNameForIssuerIdentificationNumber(String issuerIdentificationNumber) {
        return cardFactoryDao.getNameForIssuerIdentificationNumber(issuerIdentificationNumber);
    }

    @Override
    public CreditCard createNewCreditCard(Account account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
