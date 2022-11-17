package com.ncs503.Babybook.controller;
import com.ncs503.Babybook.models.request.SubjectRequest;
import com.ncs503.Babybook.models.response.PaginationResponse;
import com.ncs503.Babybook.models.response.SubjectResponse;
import com.ncs503.Babybook.service.SubjectService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping
    @ApiOperation(value = "Create subjects", notes = "Allows User to insert subjects")
    @ApiResponses({@ApiResponse(code = 201, message = "Subject created!")})
    public ResponseEntity<SubjectResponse> createSubject (@Valid @RequestBody SubjectRequest request,
                                                          @RequestHeader(name="Authorization") String token) throws IOException {

        SubjectResponse responseCreate = this.subjectService.create(request, token);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseCreate);
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "Soft Delete subject By ID", notes = "Allows User to delete subject by ID")
    @ApiResponses({@ApiResponse(code = 204, message = "Subject deleted!"),
            @ApiResponse(code = 404, message = "The inserted ID does not belong to a business")})
    public ResponseEntity<Void> deleteSubject (@PathVariable @Valid @NotNull @NotBlank @ApiParam(
                                                name = "id",
                                                type = "Long",
                                                value = "ID of the subject requested",
                                                example = "1",
                                                required = true) Long id){

        this.subjectService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("{id}")
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
                                                                    required = true) SubjectRequest request) throws IOException {

        SubjectResponse response = this.subjectService.update(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping("/getByUser")
    @ApiOperation(value = "Get Subject By User ID", notes = "Returns all the subject according to the User")
    @ApiResponses({@ApiResponse(code = 200, message = "Return the requested subjects"),
            @ApiResponse(code = 404, message = "The inserted ID does not belong to a user")})
    public ResponseEntity<?> getByUser(@RequestParam (required = true)  @ApiParam(
            name = "userID",
            type = "Long",
            value = "ID of the user requested",
            example = "1",
            required = true) Long userID,  @RequestParam (required = false, defaultValue = "ASC") String order,  @RequestParam(value = "page", required = false) Optional<Integer> page,
                                       @RequestParam(value = "size", required = false) Optional<Integer> size) {

        return new ResponseEntity<>(subjectService.getSubjectByUsers(userID,order,page, size), HttpStatus.OK);
    }


    @GetMapping("/getByName")
    @ApiOperation(value = "Get Subject By name", notes = "Returns all the subject according to the name")
    @ApiResponses({@ApiResponse(code = 200, message = "Return the requested subjects"),
            @ApiResponse(code = 404, message = "The inserted NAME does not belong to a user")})
    public ResponseEntity <PaginationResponse> getByFilters (@RequestParam (required = false) @ApiParam(name = "firstName",
                                                            type = "String",
                                                            value = "name of the Subject",
                                                            example = "Pepito") String name,
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
                                                                     example = "3") Optional<Integer> size) {

        return new ResponseEntity<>(subjectService.getSubjectByName(name, order, page, size),
                HttpStatus.OK);

    }




}
