package com.ncs503.Babybook.controller;

import com.ncs503.Babybook.models.response.EventResponse;
import com.ncs503.Babybook.models.utility.TagsEventEnum;
import com.ncs503.Babybook.service.EventService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("events")
@Api(description ="Events CRUD" , tags = "Events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    @ApiOperation(value = "Create business", notes = "Allows Admin to insert business")
    @ApiResponses({@ApiResponse(code = 201, message = "Business created!")})
    public ResponseEntity<EventResponse> create(@Valid @RequestHeader(name="Authorization") String token,

                                                @ApiParam( name = "title", type = "String",
                                                           example = "Primer dia de Jardin" ) @RequestParam String title,

                                                @ApiParam( name = "body", type = "String",
                                                           example = "Primer dia de clases en el Colegio San Alejandro" ) @RequestParam String body,

                                                @ApiParam( name = "date", type = "String",
                                                        example = "2022-10-23" ) @RequestParam String date,

                                                @RequestPart (required = false) List<MultipartFile> media,

                                                @ApiParam( name = "subjectId", type = "Long",
                                                           example = "Santa Fe" ) @RequestParam Long subjectId,

                                                @ApiParam( name = "eventEnumList", type = "List<TagsEventEnum>",
                                                           example = "3516622525" ) @RequestParam List<TagsEventEnum> eventEnumList

                                                                                            )  throws IOException {

        LocalDate date1 = LocalDate.parse(date);
        EventResponse response = this.eventService.create(token, title, body, date1, media, subjectId, eventEnumList);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
