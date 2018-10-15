/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.reception;
import java.util.Date;
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
    @Override
    
    public String createCardCode(String roomNumber, Date startDate, Date endDate) {
        String cardCode= new String();
        CardKey card=new CardKey();
        SecretKeyProviderImpl skp=new SecretKeyProviderImpl();
        issNum+=1;
        
        card.setStartDate(startDate);
        card.setEndDate(endDate);
        card.setRoomNumber(roomNumber);
        card.setIssueNumber(issNum);
        cardCode=skp.encodeCard(card);
        return cardCode;
    }

    @Override
    public CardKey readCard(String cardCode) {
        CardKey card=new CardKey();
        SecretKeyProviderImpl skp=new SecretKeyProviderImpl();
        
        card=skp.decodeCard(cardCode);
        return card;
    }

    @Override
    public void setSecretKeyProvider(SecretKeyProvider secretKeyProvider) {
        
    }
    
}
