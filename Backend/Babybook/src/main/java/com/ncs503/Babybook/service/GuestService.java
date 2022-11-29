package com.ncs503.Babybook.service;

import com.ncs503.Babybook.exception.GuestNotFoundException;
import com.ncs503.Babybook.exception.InvalidGuestException;
import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.exception.UserNotFoundException;
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

    public List<GuestResponse> getGuestsByUser(String token, Long user_id) throws GuestNotFoundException, InvalidUserException, UserNotFoundException;

    public GuestResponse getGuest(Long id, String token, Long user_id) throws GuestNotFoundException, InvalidUserException, UserNotFoundException, InvalidGuestException;

    public GuestResponse saveGuest(GuestRequest guestReq, String token, Long user_id) throws InvalidUserException, InvalidGuestException, GuestNotFoundException, UserNotFoundException;

    public void deleteGuest(Long id, String token, Long user_id) throws GuestNotFoundException, InvalidUserException, UserNotFoundException;

    public void adminDeleteGuest(Long id, String token) throws GuestNotFoundException, InvalidUserException;

    public GuestResponse updateGuest(GuestRequest guestReq, Long id, String token, Long user_id) throws InvalidUserException, InvalidGuestException, GuestNotFoundException, UserNotFoundException;
}
