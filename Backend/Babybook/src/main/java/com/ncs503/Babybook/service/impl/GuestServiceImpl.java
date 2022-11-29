package com.ncs503.Babybook.service.impl;

import com.ncs503.Babybook.auth.filter.JwtUtils;
import com.ncs503.Babybook.auth.utility.RoleEnum;
import com.ncs503.Babybook.exception.GuestNotFoundException;
import com.ncs503.Babybook.exception.InvalidGuestException;
import com.ncs503.Babybook.exception.InvalidUserException;
import com.ncs503.Babybook.exception.UserNotFoundException;
import com.ncs503.Babybook.models.entity.GuestEntity;
import com.ncs503.Babybook.models.entity.UserEntity;
import com.ncs503.Babybook.models.mapper.GuestMapper;
import com.ncs503.Babybook.models.request.GuestRequest;
import com.ncs503.Babybook.models.response.GuestResponse;
import com.ncs503.Babybook.models.response.UserResponse;
import com.ncs503.Babybook.repository.GuestRepository;
import com.ncs503.Babybook.repository.UserRepository;
import com.ncs503.Babybook.service.GuestService;
import com.ncs503.Babybook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private UserRepository userRepo;

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public List<GuestResponse> getGuests() throws GuestNotFoundException {
        return guestMapper.guestsToGuestResponseList(guestRepo.findAll());
    }

    @Override
    public List<GuestResponse> getGuestsByUser(String token, Long user_id) throws GuestNotFoundException, InvalidUserException, UserNotFoundException {

        UserEntity user = this.getUserByToken(token);
        assert user != null;
        if(user.getId().equals(user_id)) {
            List<GuestEntity> guests = user.getGuests();
            return guestMapper.guestsToGuestResponseList(guests);
        }
        else throw new InvalidUserException("Invalid User");

    }

    @Override
    public GuestResponse getGuest(Long id, String token, Long user_id) throws GuestNotFoundException, InvalidUserException, UserNotFoundException, InvalidGuestException {
        UserEntity user = this.getUserByToken(token);
        assert user != null;
        if(user.getId().equals(user_id)){
            GuestEntity guest = guestRepo.findById(id).orElse(null);
            List<GuestEntity> guests = user.getGuests();
            if (guests.contains(guest)) {
                return guestMapper.toGuestResponse(guest);
            }
            else throw new InvalidGuestException("Guest do not exist");
        }
        else throw new InvalidUserException("Invalid user");

    }

    @Override
    public GuestResponse saveGuest(GuestRequest guestReq, String token, Long user_id) throws InvalidGuestException, GuestNotFoundException, UserNotFoundException, InvalidUserException {
        UserEntity user = this.getUserByToken(token);
        assert user != null;
        if(user.getId().equals(user_id)){
            GuestEntity guest = guestMapper.toGuestEntity(guestReq);
            guest.setUser_id(user);
            guestRepo.save(guest);
            return guestMapper.toGuestResponse(guest);
        }
        else throw new InvalidUserException("Invalid user");


    }

    @Override
    public void deleteGuest(Long id, String token, Long user_id) throws GuestNotFoundException, UserNotFoundException, InvalidUserException {
        UserEntity user = this.getUserByToken(token);
        assert user != null;
        if(user.getId().equals(user_id)){
            GuestEntity guest = guestRepo.findById(id).orElse(null);
            List<GuestEntity> guests = user.getGuests();
            if (guests.contains(guest)) {
//                System.out.println("borrando guests");
                guestRepo.deleteById(id);
            } else throw new GuestNotFoundException("Guest not found");
        }
        else throw new InvalidUserException("Invalid user");

    }

    @Override
    public void adminDeleteGuest(Long id) throws GuestNotFoundException, InvalidUserException {
       guestRepo.deleteById(id);
    }



    @Override
    public GuestResponse updateGuest(GuestRequest guestReq, Long id, String token, Long user_id) throws InvalidGuestException, GuestNotFoundException, InvalidUserException, UserNotFoundException {
        UserEntity user = this.getUserByToken(token);
        assert user != null;
        if(user.getId().equals(user_id)){
            GuestEntity updatedGuest = guestMapper.toGuestEntity(guestReq);
            GuestEntity updatableGuest = guestRepo.findById(id).orElse(null);
            List<GuestEntity> guests = user.getGuests();
            if (guests.contains(updatableGuest)) {
                updatedGuest.setId(id);
                UserEntity user2 = userRepo.getById(user_id);
                updatedGuest.setUser_id(user2);
                guestRepo.save(updatedGuest);
                return guestMapper.toGuestResponse(updatedGuest);
            } else throw new GuestNotFoundException("Guest not found");
        }
        else throw new InvalidUserException("Invalid User");

    }

    @Override
    public GuestResponse adminUpdateGuest(GuestRequest guestReq, Long id) throws InvalidGuestException, GuestNotFoundException {
       GuestEntity guest = guestMapper.toGuestEntity(guestReq);
       guest.setId(id);
       guestRepo.save(guest);
       return guestMapper.toGuestResponse(guest);
    }

    public String getToken(String token) {
        String[] part = token.split(" ");
        String tokenWithoutBearer = part[1];
        return tokenWithoutBearer;

    }

    public UserEntity getUserByToken(String token){
        String token2 = this.getToken(token);
        return userRepo.findByEmail(jwtUtils.extractUsername(token2)).orElse(null);
    }

}
