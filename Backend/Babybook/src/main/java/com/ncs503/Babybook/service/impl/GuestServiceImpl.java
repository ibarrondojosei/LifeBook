package com.ncs503.Babybook.service.impl;

import com.ncs503.Babybook.auth.filter.JwtUtils;
import com.ncs503.Babybook.exception.GuestNotFoundException;
import com.ncs503.Babybook.exception.InvalidGuestException;
import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.models.entity.GuestEntity;
import com.ncs503.Babybook.models.mapper.GuestMapper;
import com.ncs503.Babybook.models.request.GuestRequest;
import com.ncs503.Babybook.models.response.GuestResponse;
import com.ncs503.Babybook.repository.GuestRepository;
import com.ncs503.Babybook.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leonardo Terlizzi
 */

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepo;

    @Autowired
    private GuestMapper guestMapper;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public List<GuestResponse> getGuests() throws GuestNotFoundException {
        return guestMapper.guestsToGuestResponseList(guestRepo.findAll());
    }

    @Override
    public GuestResponse getGuest(Long id, String token) throws GuestNotFoundException, InvalidUserException {
        String userToken = getToken(token);

        return guestMapper.toGuestResponse(guestRepo.findById(id).orElse(null));
    }

    @Override
    public GuestResponse saveGuest(GuestRequest guestReq, String token) throws InvalidGuestException, GuestNotFoundException {
        GuestEntity guest = guestMapper.toGuestEntity(guestReq);
        guestRepo.save(guest);
        return guestMapper.toGuestResponse(guest);

    }

    @Override
    public void deleteGuest(Long id, String token) throws GuestNotFoundException {
        String userToken = getToken(token);

        guestRepo.deleteById(id);
    }

    @Override
    public GuestResponse updateGuest(GuestRequest guestReq, Long id, String token) throws InvalidGuestException, GuestNotFoundException, InvalidUserException {
        String userToken = getToken(token);

        GuestEntity guest = guestMapper.toGuestEntity(guestReq);
        guest.setId(id);
        guestRepo.save(guest);
        return guestMapper.toGuestResponse(guest);
    }

    public String getToken(String token){
        String [] part = token.split(" ");
        String tokenWithoutBearer = part[1];
        return tokenWithoutBearer;

    }
