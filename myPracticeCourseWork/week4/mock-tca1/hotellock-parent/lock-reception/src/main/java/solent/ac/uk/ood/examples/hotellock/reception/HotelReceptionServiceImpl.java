/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.reception;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solent.ac.uk.ood.examples.hotellock.model.CardKey;
import solent.ac.uk.ood.examples.hotellock.model.HotelReceptionService;
import solent.ac.uk.ood.examples.hotellock.model.SecretKeyProvider;
import solent.ac.uk.ood.examples.hotellock.secretkey.SecretKeyProviderImpl;



/**
 *
 * @author 3lelia74
 */
public class HotelReceptionServiceImpl implements HotelReceptionService {
    static int issNum=0;
    private SecretKeyProvider skp;
    public static final Logger LOG = LogManager.getLogger(HotelReceptionServiceImpl.class);
    
    public static final Logger TRANSACTIONLOG = LogManager.getLogger("transaction-log");
    @Override
    
    public String createCardCode(String roomNumber, Date startDate, Date endDate) {
        setSecretKeyProvider(new SecretKeyProviderImpl());
        String cardCode= new String();
        CardKey card=new CardKey();
        issNum+=1;
        
        card.setStartDate(startDate);
        card.setEndDate(endDate);
        card.setRoomNumber(roomNumber);
        card.setIssueNumber(issNum);
        cardCode=skp.encodeCard(card);
        
        String message = "Reception created new card from " + card.toString() + " to encodedCard=" + cardCode;
        LOG.info(message);
        TRANSACTIONLOG.info(message);
        
        return cardCode;
    }

    @Override
    public CardKey readCard(String cardCode) {
        CardKey card=new CardKey();
        setSecretKeyProvider(new SecretKeyProviderImpl());
               
        card=skp.decodeCard(cardCode);
        LOG.debug("Hotel reception decoded card " + card + " From encodedCard=" + cardCode);
        return card;
    }

    @Override
    public void setSecretKeyProvider(SecretKeyProvider secretKeyProvider) {
        skp=secretKeyProvider;
    }
    
}
