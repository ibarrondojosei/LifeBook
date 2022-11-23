package com.ncs503.Babybook.controller;

import com.ncs503.Babybook.exception.GuestNotFoundException;
import com.ncs503.Babybook.exception.InvalidGuestException;
import com.ncs503.Babybook.models.entity.GuestEntity;
import com.ncs503.Babybook.models.mapper.GuestMapper;
import com.ncs503.Babybook.models.request.GuestRequest;
import com.ncs503.Babybook.models.response.GuestResponse;
import com.ncs503.Babybook.repository.GuestRepository;
import com.ncs503.Babybook.service.GuestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Leonardo Terlizzi
 */
@RestController
@RequestMapping(path = "/guest")
@Api(description = "Guest CRUD", tags = "Guests")
public class GuestEntityController {

    @Autowired
    private GuestService guestServ;

    @Autowired
    private GuestMapper guestMapper;

    @Autowired
    private GuestRepository guestRepo;

    @GetMapping("/all")
    @ApiOperation(value="List all the guests", notes = "Endpoint that return a list of all the guests")
    @ApiResponses(value= {
            @ApiResponse(code= 201, message = "Guest's List"),
            @ApiResponse(code=403, message = "Forbidden action")
    })
    public ResponseEntity<List<GuestResponse>> getGuests() throws GuestNotFoundException{
        List<GuestResponse> guests = guestServ.getGuests();
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value="Guest", notes = "Endpoint that return a guest by its id")
    @ApiResponses(value= {
            @ApiResponse(code= 201, message = "Guest response"),
            @ApiResponse(code=403, message = "Forbidden action")
    })
    public ResponseEntity<GuestResponse> getGuest(Long id) throws GuestNotFoundException {
        return new ResponseEntity<>(guestServ.getGuest(id), HttpStatus.OK);
    }

    @PostMapping("/new")
    @ApiOperation(value="Create new guest", notes = "Endpoint that create a new guest")
    @ApiResponses(value= {
            @ApiResponse(code= 201, message = "Guest created!"),
            @ApiResponse(code=403, message = "Forbidden action")
    })
    public ResponseEntity<GuestResponse> saveGuest(@RequestBody GuestRequest guestReq) throws InvalidGuestException, GuestNotFoundException {
        guestServ.saveGuest(guestReq);
        GuestEntity guest = guestRepo.findByEmail(guestReq.getEmail()).orElse(null);
        GuestResponse guestRes = guestMapper.toGuestResponse(guest);
        return new ResponseEntity<>(guestRes, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value="Delete a guest", notes = "Endpoint that allow an user to delete a guest")
    @ApiResponses(value= {
            @ApiResponse(code= 201, message = "Guest deleted!"),
            @ApiResponse(code=403, message = "Forbidden action")
    })
    public ResponseEntity<Void> deleteGuest(@RequestParam Long id) throws GuestNotFoundException {
        guestServ.deleteGuest(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/su/delete")
    @ApiOperation(value="Delete a guest(admin)", notes = "Endpoint that allow an admin to delete a guest")
    @ApiResponses(value= {
            @ApiResponse(code= 201, message = "Guest deleted!"),
            @ApiResponse(code=403, message = "Forbidden action")
    })
    public ResponseEntity<Void> adminDeleteGuest(@RequestParam Long id) throws GuestNotFoundException {
        guestServ.deleteGuest(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/update")
    @ApiOperation(value="Update a guest", notes = "Endpoint that allow an user to update a guest")
    @ApiResponses(value= {
            @ApiResponse(code= 201, message = "Guest updated!"),
            @ApiResponse(code=403, message = "Forbidden action")
    })
    public ResponseEntity<GuestResponse> updateGuest(@RequestBody GuestRequest guestReq, @RequestParam Long id) throws InvalidGuestException, GuestNotFoundException{
        guestServ.updateGuest(guestReq, id);
        GuestEntity guest = guestRepo.findByEmail(guestReq.getEmail()).orElse(null);
        GuestResponse guestRes = guestMapper.toGuestResponse(guest);
        return new ResponseEntity<>(guestRes, HttpStatus.OK);
    }

    @PatchMapping("/su/update")
    @ApiOperation(value="Update a guest(admin)", notes = "Endpoint that allow an admin to update a guest")
    @ApiResponses(value= {
            @ApiResponse(code= 201, message = "Guest updated!"),
            @ApiResponse(code=403, message = "Forbidden action")
    })
    public ResponseEntity<GuestResponse> adminUpdateGuest(@RequestBody GuestRequest guestReq, @RequestParam Long id) throws InvalidGuestException, GuestNotFoundException{
        guestServ.updateGuest(guestReq, id);
        GuestEntity guest = guestRepo.findByEmail(guestReq.getEmail()).orElse(null);
        GuestResponse guestRes = guestMapper.toGuestResponse(guest);
        return new ResponseEntity<>(guestRes, HttpStatus.OK);
    }
}
