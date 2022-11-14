package com.ncs503.Babybook.controller;

import com.ncs503.Babybook.models.response.MedicalRecordResponse;
import com.ncs503.Babybook.models.response.MedicalRecordResponse;
import com.ncs503.Babybook.models.utility.TagsMedicalRecordEnum;
import com.ncs503.Babybook.service.MedicalRecordService;
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
@RequestMapping("medicalRecord")
@Api(description ="MedicalRecords CRUD" , tags = "MedicalRecord")
@CrossOrigin(origins = "http://localhost:3000")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;


    @PostMapping("/create")
    @ApiOperation(value = "Create business", notes = "Allows Admin to insert business")
    @ApiResponses({@ApiResponse(code = 201, message = "Business created!")})
    public ResponseEntity<MedicalRecordResponse> create(@Valid @RequestHeader(name="Authorization") String token,

                                                        @ApiParam( name = "title", type = "String",
                                                        example = "Primer dia de Jardin" ) @RequestParam String title,

                                                        @ApiParam( name = "body", type = "String",
                                                        example = "Primer dia de clases en el Colegio San Alejandro" ) @RequestParam String body,

                                                        @ApiParam( name = "date", type = "String",
                                                        example = "2022-10-23" ) @RequestParam String date,

                                                        @RequestPart (required = false) List<MultipartFile> media,

                                                        @ApiParam( name = "subjectId", type = "Long",
                                                        example = "Santa Fe" ) @RequestParam Long subjectId,

                                                        @ApiParam( name = "MedicalRecordEnumList", type = "List<TagsMedicalRecordEnum>",
                                                        example = "3516622525" ) @RequestParam List<TagsMedicalRecordEnum> MedicalRecordEnumList

    )  throws IOException {

        LocalDate date1 = LocalDate.parse(date);
        MedicalRecordResponse response = this.medicalRecordService.create(token, title, body, date1, media, subjectId, MedicalRecordEnumList);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
