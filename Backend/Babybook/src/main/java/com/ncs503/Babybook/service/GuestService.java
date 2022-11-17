package com.ncs503.Babybook.service;

import com.ncs503.Babybook.exception.GuestNotFoundException;
import com.ncs503.Babybook.exception.InvalidGuestException;
import com.ncs503.Babybook.models.request.GuestRequest;
import com.ncs503.Babybook.models.response.GuestResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leonardo Terlizzi
 */

@Service
public interface GuestService {

    public List<GuestResponse> getGuests() throws GuestNotFoundException;

    public GuestResponse getGuest(Long id) throws GuestNotFoundException;

    public void saveGuest(GuestRequest guestReq) throws InvalidGuestException;

    public void deleteGuest(Long id) throws GuestNotFoundException;

    public void updateGuest(GuestRequest guestReq, Long id) throws InvalidGuestException, GuestNotFoundException;
}
