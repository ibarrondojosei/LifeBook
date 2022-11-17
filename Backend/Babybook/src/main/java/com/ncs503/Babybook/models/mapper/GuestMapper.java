package com.ncs503.Babybook.models.mapper;

import com.ncs503.Babybook.exception.GuestNotFoundException;
import com.ncs503.Babybook.exception.InvalidGuestException;
import com.ncs503.Babybook.models.entity.GuestEntity;
import com.ncs503.Babybook.models.request.GuestRequest;
import com.ncs503.Babybook.models.response.GuestResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonardo Terlizzi
 */

@Component
public class GuestMapper {

    public GuestEntity toGuestEntity(GuestRequest guestRes) throws InvalidGuestException {
        GuestEntity guest = new GuestEntity();
        guest.setId(guestRes.getId());
        guest.setFirstName(guest.getFirstName());
        guest.setLastName(guest.getLastName());
        guest.setEmail(guest.getEmail());
        return guest;
    }

    public GuestResponse toGuestResponse(GuestEntity guest) throws GuestNotFoundException{
        GuestResponse guestRes = new GuestResponse();
        guestRes.setId(guest.getId());
        guestRes.setFirstName(guest.getFirstName());
        guestRes.setLastName(guest.getLastName());
        guestRes.setEmail(guest.getEmail());
        return guestRes;
    }

    public List<GuestResponse> guestsToGuestResponseList(List<GuestEntity> guests) throws GuestNotFoundException {
        List<GuestResponse> guestResList = new ArrayList<>();
        guests.forEach(guest ->{
            try{
                guestResList.add(toGuestResponse(guest));
            } catch (GuestNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        return guestResList;
    }
}
