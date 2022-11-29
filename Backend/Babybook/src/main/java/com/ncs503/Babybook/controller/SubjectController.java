package com.ncs503.Babybook.controller;
import com.ncs503.Babybook.models.request.SubjectRequest;
import com.ncs503.Babybook.models.response.PaginationResponse;
import com.ncs503.Babybook.models.response.SubjectResponse;
import com.ncs503.Babybook.service.SubjectService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("subjects")
@Api(description ="Subjects CRUD" , tags = "Subjects")
@CrossOrigin(origins = "http://localhost:3000")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;


//    @PostMapping //FUNCIONA
    @PostMapping(consumes = {"*/*"})
    @ApiOperation(value = "Create subjects", notes = "Allows User to insert subjects")
    @ApiResponses({@ApiResponse(code = 201, message = "Subject created!")})
    public ResponseEntity<SubjectResponse> createSubject (@Valid @RequestBody SubjectRequest request,
                                                          @RequestHeader(name="Authorization") String token) throws IOException {

        SubjectResponse responseCreate = this.subjectService.create(request, token);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseCreate);
    }


    @DeleteMapping("{id}")//FUNCIONA
    @ApiOperation(value = "Soft Delete subject By ID", notes = "Allows User to delete subject by ID")
    @ApiResponses({@ApiResponse(code = 204, message = "Subject deleted!"),
            @ApiResponse(code = 404, message = "The inserted ID does not belong to a business")})
    public ResponseEntity<Void> deleteSubject (@PathVariable @Valid @NotNull @NotBlank @ApiParam(
                                                name = "id",
                                                type = "Long",
                                                value = "ID of the subject requested",
                                                example = "1",
                                                required = true) Long id,
                                               @RequestHeader(name="Authorization") String token){



        this.subjectService.delete(id, token);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("{id}")//FUNCIONA
    @ApiOperation(value = "Update Subject By ID", notes = "Allows User to update an existing Subject by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Subject updated!"),
            @ApiResponse(code = 404, message = "The inserted ID does not belong to a subject")})
    public ResponseEntity<SubjectResponse> updateSubject (@PathVariable @Valid @NotNull @NotBlank  @ApiParam(
                                                            name = "id",
                                                            type = "Long",
                                                            value = "ID of the subject requested",
                                                            example = "1",
                                                            required = true) Long id,
                                                            @Valid @RequestBody @ApiParam(
                                                                    name = "New Business",
                                                                    value = "Business to save",
                                                                    required = true) SubjectRequest request,
                                                            @RequestHeader(name="Authorization") String token) throws IOException {

        SubjectResponse response = this.subjectService.update(id, request, token);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping("/getByUser")//FUNCIONA
    @ApiOperation(value = "Get Subject By User ID", notes = "Returns all the subject according to the User")
    @ApiResponses({@ApiResponse(code = 200, message = "Return the requested subjects"),
            @ApiResponse(code = 404, message = "The inserted ID does not belong to a user")})
    public ResponseEntity<?> getByUser( @RequestParam (required = false, defaultValue = "ASC") String order,
                                        @RequestParam(value = "page", required = false) Optional<Integer> page,
                                        @RequestParam(value = "size", required = false) Optional<Integer> size,
                                        @RequestHeader(name="Authorization") String token) {

        return new ResponseEntity<>(subjectService.getSubjectByUsers(order,page, size, token), HttpStatus.OK);
    }


    @GetMapping("/getByName/{firstName}")//FUNCIONA
    @ApiOperation(value = "Get Subject By name", notes = "Returns all the subject according to the name")
    @ApiResponses({@ApiResponse(code = 200, message = "Return the requested subjects"),
            @ApiResponse(code = 404, message = "The inserted NAME does not belong to a user")})
    public ResponseEntity <PaginationResponse> getByFilters (@PathVariable("firstName") @Valid @NotNull @NotBlank @ApiParam(name = "firstName",
                                                            type = "String",
                                                            value = "name of the Subject",
                                                            example = "Pepito") String firstName,
                                                             @RequestParam (required = false, defaultValue = "ASC") String order,
                                                             @RequestParam(value = "page", required = false)@ApiParam(
                                                                     name = "page",
                                                                     type = "Integer",
                                                                     value = "page number I want to see",
                                                                     example = "1")Optional<Integer> page,
                                                             @RequestParam(value = "size", required = false)@ApiParam(
                                                                     name = "size",
                                                                     type = "Integer",
                                                                     value = "number of items per page",
                                                                     example = "3") Optional<Integer> size,
                                                             @RequestHeader(name="Authorization") String token) {

        return new ResponseEntity<>(subjectService.getSubjectByName(firstName, order, page, size, token),
                HttpStatus.OK);

    }




}
