/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.lock;
import solent.ac.uk.ood.examples.hotellock.model.CardKey;
import solent.ac.uk.ood.examples.hotellock.model.HotelRoomLockService;
import solent.ac.uk.ood.examples.hotellock.model.SecretKeyProvider;
import solent.ac.uk.ood.examples.hotellock.secretkey.SecretKeyProviderImpl;
import java.util.Date;

/**
 *
 * @author 3lelia74
 */
public class HotelRoomLockServiceImpl implements HotelRoomLockService{
    private SecretKeyProvider skp;
    private String roomNum;
    @Override
    public String unlockDoor(String cardCode) {
        CardKey card=new CardKey();
        setSecretKeyProvider(new SecretKeyProviderImpl());
        Date currentDate=new Date();
        if((card.getRoomNumber().equals(roomNum))||(card.getRoomNumber().equals("*"))){
            if((currentDate.after(card.getStartDate())|| (currentDate.equals(card.getStartDate()))) ){
                if((currentDate.before(card.getEndDate())|| (currentDate.equals(card.getEndDate()))) ){
                    return("Door opened");
                }
            }
        }
        return("Door closed");
    }

    @Override
    public void setSecretKeyProvider(SecretKeyProvider secretKeyProvider) {
        skp=secretKeyProvider;
    }

    @Override
    public void setRoomNumber(String roomNumber) {
        roomNum=roomNumber;
    }
    
}
