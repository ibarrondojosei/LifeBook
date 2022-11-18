package com.ncs503.Babybook.controller;

import com.ncs503.Babybook.models.response.EventResponse;
import com.ncs503.Babybook.models.utility.TagsEventEnum;
import com.ncs503.Babybook.service.EventService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("events")
@Api(description ="Events CRUD" , tags = "Events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    @ApiOperation(value = "Create Events", notes = "method that creates events")
    @ApiResponses({@ApiResponse(code = 201, message = "Events created!")})
    public ResponseEntity<EventResponse> create(@Valid
//                                                    @RequestHeader(name="Authorization") String token,
                                                @ApiParam( name = "title", type = "String",
                                                           example = "Primer dia de Jardin" ) @RequestParam (required = false) String title,
                                                @ApiParam( name = "bodie", type = "String",
                                                           example = "ajajajajajajajjajaja " ) @RequestParam (required = false) String bodie,
                                                @ApiParam( name = "date", type = "String",
                                                        example = "2022-10-23" ) @RequestParam (required = false) String date,
                                                @RequestPart (required = false) List<MultipartFile> media,
                                                @ApiParam( name = "subjectId", type = "Long",
                                                           example = "1" ) @RequestParam Long subjectId,
                                                @ApiParam( name = "eventEnum", type = "TagsEventEnum",
                                                           example = "CUMPLEAÑOS" ) @RequestParam (required = false) TagsEventEnum eventEnum
                                                                                            )  throws IOException {
        String token = "aaa";
//        Long subjectId = 1l;

        LocalDate date1 = LocalDate.parse(date);

        EventResponse response = eventService.create(token, title, bodie, date1, media, subjectId, eventEnum);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping("/update")
    @ApiOperation(value = "update Events", notes = "method that updates events by id")
    @ApiResponses({@ApiResponse(code = 200, message = "Events modificated!")})
    public ResponseEntity<EventResponse> update(@Valid
//                                                    @RequestHeader(name="Authorization") String token,
                                                @ApiParam( name = "eventId", type = "Long",
                                                        example = "1" ) @RequestParam (required = false) Long eventId,
                                                @ApiParam( name = "title", type = "String",
                                                        example = "Primer dia de Jardin" ) @RequestParam (required = false) String title,
                                                @ApiParam( name = "bodie", type = "String",
                                                        example = "ajajajajajajajjajaja " ) @RequestParam (required = false) String bodie,
                                                @ApiParam( name = "date", type = "String",
                                                        example = "2022-10-23" ) @RequestParam (required = false) String date,
                                                @RequestPart (required = false) List<MultipartFile> media,
                                                @ApiParam( name = "subjectId", type = "Long",
                                                           example = "1" ) @RequestParam Long subjectId,
                                                @ApiParam( name = "eventEnum", type = "TagsEventEnum",
                                                        example = "CUMPLEAÑOS" ) @RequestParam (required = false) TagsEventEnum eventEnum
                                                                                         )  throws IOException {
        String token = "aaa";
//        Long subjectId = 1l;

        LocalDate date1 = LocalDate.parse(date);

        EventResponse response = eventService.update(token, eventId, title, bodie, date1, media, subjectId, eventEnum);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Delete Events", notes = "method that deleted the events by id")
    @ApiResponses({@ApiResponse(code = 202, message = "Events deleted!")})
    public ResponseEntity<EventResponse> delete(@Valid
//                                                @RequestHeader(name="Authorization") String token,
                                                  @ApiParam( name = "subjectId", type = "Long",
                                                          example = "1" ) @RequestParam (required = false) Long subjectId,
                                                  @ApiParam( name = "eventId", type = "Long",
                                                          example = "1" ) @RequestParam (required = false) Long eventId
                                                                                )  throws IOException {
        String token = "aaa";

        eventService.delete(token, subjectId, eventId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/findById")
    @ApiOperation(value = "findById Events", notes = "method that shows the events by id")
    @ApiResponses({@ApiResponse(code = 200, message = "Events modificated!")})
    public ResponseEntity<EventResponse> findById(@Valid
//                                                @RequestHeader(name="Authorization") String token,
                                                  @ApiParam( name = "subjectId", type = "Long",
                                                          example = "1" ) @RequestParam (required = false) Long subjectId,
                                                  @ApiParam( name = "eventId", type = "Long",
                                                          example = "1" ) @RequestParam (required = false) Long eventId
                                                                                    ) throws Exception {
        String token = "aaa";

        EventResponse response = eventService.findById(token, subjectId, eventId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/findAllByDate")
    @ApiOperation(value = "findAllByDate Events", notes = "method that shows the events by id")
    @ApiResponses({@ApiResponse(code = 200, message = "Events modificated!")})
    public ResponseEntity<List<EventResponse>> findAllByDate(@Valid
//                                                @RequestHeader(name="Authorization") String token,
                                                  @ApiParam( name = "subjectId", type = "Long",
                                                      example = "1" ) @RequestParam (required = false) Long subjectId,
                                                  @ApiParam( name = "date", type = "String",
                                                      example = "2022-10-23" ) @RequestParam (required = false, defaultValue = "${new java.util.Date()}") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.util.Date date
                                                                                  ) throws Exception {

        String token = "aaa";

        List<EventResponse> response = eventService.findAllByDate(token, subjectId, date.toInstant()
                                                                            .atZone(ZoneId.systemDefault())
                                                                            .toLocalDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @GetMapping("/findAllByTags")
    @ApiOperation(value = "findAllByTags Events", notes = "method that shows the events by tags")
    @ApiResponses({@ApiResponse(code = 200, message = "Events modificated!")})
    public ResponseEntity<List<EventResponse>> findAllByTags(@Valid
//                                                  @RequestHeader(name="Authorization") String token,
                                                  @ApiParam( name = "subjectId", type = "Long",
                                                        example = "1" ) @RequestParam Long subjectId,
                                                  @ApiParam( name = "eventEnum", type = "TagsEventEnum",
                                                        example = "CRECIMIENTO" ) @RequestParam (required = false) TagsEventEnum eventEnum
                                                                                 ) throws Exception {

        String token = "aaa";

        List<EventResponse> response = eventService.findAllByTags(token, subjectId, eventEnum);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }



}
