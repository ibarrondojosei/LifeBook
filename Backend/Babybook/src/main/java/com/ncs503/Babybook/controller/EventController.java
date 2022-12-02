package com.ncs503.Babybook.controller;

import com.ncs503.Babybook.models.response.EventFilterBySubjectResponse;
import com.ncs503.Babybook.models.response.EventResponse;
import com.ncs503.Babybook.models.response.PaginationResponse;
import com.ncs503.Babybook.models.utility.TagsEventEnum;
import com.ncs503.Babybook.service.EventService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("events")
@Api(description ="Events CRUD" , tags = "Events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    @Autowired
    private EventService eventService;

    @Transactional
    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiOperation(value = "Create Events", notes = "method that creates events")
    @ApiResponses({@ApiResponse(code = 201, message = "Events created!")})
    public ResponseEntity<EventResponse> create(@Valid
                                                    @RequestHeader(name = "Authorization") String token,
                                                @RequestPart(required = false) List<MultipartFile> media,
                                                @ApiParam(name = "title", type = "String", example = "Primer dia de Jardin")
                                                    @RequestParam(required = false) String title,
                                                @ApiParam(name = "bodie", type = "String", example = "ajajajajajajajjajaja ")
                                                    @RequestParam(required = false) String bodie,
                                                @ApiParam(name = "date", type = "String", example = "2022-10-23")
                                                    @RequestParam(required = false) String date,
                                                @ApiParam(name = "highlightMoment", type = "Boolean", example = "true - false")
                                                    @RequestParam(required = false) Boolean highlightMoment,
                                                @ApiParam(name = "subjectId", type = "Long", example = "1")
                                                    @RequestParam Long subjectId,
                                                @ApiParam(name = "eventEnum", type = "TagsEventEnum", example = "CUMPLEAÑOS")
                                                    @RequestParam(required = false) TagsEventEnum eventEnum
    ) throws Exception {

        LocalDate date1 = LocalDate.parse(date);

        EventResponse response = eventService.create(token, title, bodie, date1, media, highlightMoment, subjectId, eventEnum);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Transactional
    @PutMapping(value = "/update", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiOperation(value = "update Events", notes = "method that updates events by id")
    @ApiResponses({@ApiResponse(code = 200, message = "Events modificated!")})
    public ResponseEntity<EventResponse> update(@Valid
                                                @RequestHeader(name = "Authorization") String token,
                                                @RequestPart(required = false) List<MultipartFile> media,
                                                @ApiParam(name = "eventId", type = "Long", example = "1")
                                                    @RequestParam(required = false) Long eventId,
                                                @ApiParam(name = "title", type = "String", example = "comienzo de clases")
                                                    @RequestParam(required = false) String title,
                                                @ApiParam(name = "bodie", type = "String", example = "primer dia de Secundaria ")
                                                    @RequestParam(required = false) String bodie,
                                                @ApiParam(name = "date", type = "String", example = "2022-10-23")
                                                    @RequestParam(required = false) String date,
                                                @ApiParam(name = "highlightMoment", type = "Boolean", example = "true - false")
                                                    @RequestParam(required = false) Boolean highlightMoment,
                                                @ApiParam(name = "subjectId", type = "Long", example = "1")
                                                    @RequestParam Long subjectId,
                                                @ApiParam(name = "eventEnum", type = "TagsEventEnum", example = "CUMPLEAÑOS")
                                                    @RequestParam TagsEventEnum eventEnum
    ) throws Exception {

        LocalDate date1 = LocalDate.parse(date);

        EventResponse response = eventService.update(token, eventId, title, bodie, date1, media, highlightMoment, subjectId, eventEnum);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Transactional
    @DeleteMapping("/delete")
    @ApiOperation(value = "Delete Events", notes = "method that deleted the events by id")
    @ApiResponses({@ApiResponse(code = 204, message = "Events deleted!")})
    public ResponseEntity<EventResponse> delete(@Valid
                                                    @RequestHeader(name = "Authorization") String token,
                                                @ApiParam(name = "subjectId", type = "Long", example = "1")
                                                    @RequestParam(required = false) Long subjectId,
                                                @ApiParam(name = "eventId", type = "Long", example = "1")
                                                    @RequestParam(required = false) Long eventId
    ) throws Exception {


        eventService.delete(token, subjectId, eventId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/findByIdSubject")
    @ApiOperation(value = "findById Subjects", notes = "method that shows the events by id")
    @ApiResponses({@ApiResponse(code = 200, message = "!")})
    public ResponseEntity<List<EventFilterBySubjectResponse>> findByIdSubject(@Valid
                                                                                  @RequestHeader(name = "Authorization") String token,
                                                                              @ApiParam(name = "subjectId", type = "Long", example = "1")
                                                                                  @RequestParam(required = false) Long subjectId
    ) throws Exception {

        List<EventFilterBySubjectResponse> responses = eventService.findByIdSubject(token, subjectId);

        return ResponseEntity.status(HttpStatus.CREATED).body(responses);

    }

    @GetMapping("/findAllByTags")
    @ApiOperation(value = "findAllByTags Events", notes = "method that shows the events by tags")
    @ApiResponses({@ApiResponse(code = 200, message = "!")})
    public ResponseEntity<?> findAllByTags(@Valid
                                               @RequestHeader(name = "Authorization") String token,
                                           @ApiParam(name = "subjectId", type = "Long", example = "1")
                                               @RequestParam Long subjectId,
                                           @ApiParam(name = "eventEnum", type = "TagsEventEnum", example = "CRECIMIENTO")
                                               @RequestParam(required = false) TagsEventEnum eventEnum
    ) throws Exception {

        List<EventFilterBySubjectResponse> responses = eventService.findAllByTags(token, subjectId, eventEnum);
        return ResponseEntity.status(HttpStatus.CREATED).body(responses);

    }

    @GetMapping("/findAllByHighlightMoment")
    @ApiOperation(value = "findAllByTags Events", notes = "method that shows the events by tags")
    @ApiResponses({@ApiResponse(code = 200, message = "!")})
    public ResponseEntity<List<EventFilterBySubjectResponse>> findAllByHighlightMoment(@Valid
                                                                                           @RequestHeader(name = "Authorization") String token,
                                                                                       @ApiParam(name = "subjectId", type = "Long", example = "1")
                                                                                           @RequestParam Long subjectId
//                                                                                       @ApiParam(name = "highlightMoment", type = "Boolean", example = "true - false")
//                                                                                           @RequestParam(required = false) Boolean highlightMoment
    ) throws Exception {

        List<EventFilterBySubjectResponse> responses = eventService.findAllByHighlightMoment(token, subjectId);
        return ResponseEntity.status(HttpStatus.CREATED).body(responses);

    }


}




//    @GetMapping("/findAllByTagsPage")
//    @ApiOperation(value = "findAllByTags Events", notes = "method that shows the events by tags")
//    @ApiResponses({@ApiResponse(code = 200, message = "!")})
//    public ResponseEntity<?> findAllByTagsPage(@Valid
//                                               @RequestHeader(name = "Authorization") String token,
//                                               @ApiParam(name = "subjectId", type = "Long", example = "1")
//                                               @RequestParam Long subjectId,
//                                               @ApiParam(name = "page",type = "Integer", value = "page number", example = "1")
//                                               @RequestParam(value = "page", required = false) Optional<Integer> pageNumber,
//                                               @ApiParam(name = "size", type = "Integer", value = "number of items per page", example = "3")
//                                               @RequestParam(value = "size", required = false) Optional<Integer> size,
//                                               @ApiParam(name = "eventEnum", type = "TagsEventEnum", example = "CRECIMIENTO")
//                                               @RequestParam(required = false) TagsEventEnum eventEnum
//    ) throws Exception {
//
//        return new ResponseEntity<>(eventService.findAllByTagsPage(token, subjectId, eventEnum, pageNumber, size), HttpStatus.OK);
//
//    }



//    @GetMapping("/findAllPage")
//    @ApiOperation(value = "findById Events", notes = "method that shows the events by id")
//    @ApiResponses({@ApiResponse(code = 200, message = "Events modificated!")})
//    public ResponseEntity<PaginationResponse> findAllPage(@Valid
//                                                          @RequestHeader(name="Authorization") String token,
//                                                          @RequestParam (required = false, defaultValue = "ASC") String order,
//                                                          @ApiParam(name = "page",type = "Integer", value = "page number", example = "1")
//                                                          @RequestParam(value = "page", required = false) Optional<Integer> pageNumber,
//                                                          @ApiParam(name = "size", type = "Integer", value = "number of items per page", example = "3")
//                                                          @RequestParam(value = "size", required = false) Optional<Integer> size,
//                                                          @ApiParam( name = "eventEnum", type = "TagsEventEnum", example = "CUMPLEAÑOS" )
//                                                          @RequestParam TagsEventEnum eventEnum,
//                                                          @ApiParam( name = "subjectId", type = "Long", example = "1" )
//                                                          @RequestParam (required = false) Long subjectId
//    ) throws Exception {
//
//        return new ResponseEntity<PaginationResponse>(eventService.findAllPage(token, eventEnum, subjectId, pageNumber, size, order),
//                HttpStatus.OK);
//
//    }

//    @GetMapping("/findAllByDate")
//    @ApiOperation(value = "findAllByDate Events", notes = "method that shows the events by id")
//    @ApiResponses({@ApiResponse(code = 200, message = "Events modificated!")})
//    public ResponseEntity<List<EventFilterBySubjectResponse>> findAllByDate(@Valid
//                                                  @RequestHeader(name="Authorization") String token,
//                                                  @ApiParam( name = "subjectId", type = "Long", example = "1" )
//                                                        @RequestParam (required = false) Long subjectId,
//                                                  @ApiParam( name = "date", type = "String", example = "2022-10-23" )
//                                                        @RequestParam (required = false, defaultValue = "${new java.util.Date()}") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.util.Date date
//                                                                                  ) throws Exception {
//
//        List<EventFilterBySubjectResponse> responses = eventService.findAllByDate(token, subjectId, date.toInstant()
//                                                                            .atZone(ZoneId.systemDefault())
//                                                                            .toLocalDate());
//        return ResponseEntity.status(HttpStatus.CREATED).body(responses);

//    }


//
