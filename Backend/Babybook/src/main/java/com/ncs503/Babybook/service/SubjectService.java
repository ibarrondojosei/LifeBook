package com.ncs503.Babybook.service;

import com.ncs503.Babybook.models.request.SubjectRequest;
import com.ncs503.Babybook.models.response.PaginationResponse;
import com.ncs503.Babybook.models.response.SubjectResponse;

import java.io.IOException;
import java.util.Optional;

public interface SubjectService {

    SubjectResponse create (SubjectRequest request, String token) throws IOException;
    void delete (Long id);
    SubjectResponse update (Long id, SubjectRequest request) throws IOException;
    SubjectResponse getById (Long id) throws IOException;
    PaginationResponse getSubjectByName(String name, String order, Optional<Integer> pageNumber, Optional<Integer> size);

    PaginationResponse getSubjectByUsers(Long id, String order, Optional<Integer> page, Optional<Integer> size);
}
