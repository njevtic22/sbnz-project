package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.model.HistoryItem;
import com.ftn.sbnz.service.core.PaginatedResponse;
import com.ftn.sbnz.service.dto.historyItem.HistoryItemViewDto;
import com.ftn.sbnz.service.mapper.HistoryItemMapper;
import com.ftn.sbnz.service.security.AuthenticationService;
import com.ftn.sbnz.service.service.HistoryItemService;
import com.ftn.sbnz.service.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students/{studentId}/histories")
public class HistoryItemController {
    private final HistoryItemService service;
    private final HistoryItemMapper mapper;
    private final StudentService studentService;
    private final AuthenticationService authenticationService;

    public HistoryItemController(HistoryItemService service, HistoryItemMapper mapper, StudentService studentService, AuthenticationService authenticationService) {
        this.service = service;
        this.mapper = mapper;
        this.studentService = studentService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<PaginatedResponse<HistoryItemViewDto>> getHistory(
            @PathVariable String studentId,
            Pageable pageable
    ) {

        long sId = -1;
        if (studentId.equals("authenticated")) {
            User authenticated = authenticationService.getAuthenticated();
            sId = studentService.getByUsername(authenticated.getUsername()).getId();
        } else {
            sId = Long.parseLong(studentId);
        }

        Page<HistoryItem> history = service.getAll(sId, pageable);
        Page<HistoryItemViewDto> historyDto = history.map(mapper::toViewDto);

        PaginatedResponse<HistoryItemViewDto> responseDto = new PaginatedResponse<>(
                historyDto.getContent(),
                historyDto.getTotalElements(),
                historyDto.getTotalPages()
        );
        return ResponseEntity.ok(responseDto);
    }
}
