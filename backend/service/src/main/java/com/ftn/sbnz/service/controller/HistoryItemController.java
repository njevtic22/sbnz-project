package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.model.HistoryItem;
import com.ftn.sbnz.model.model.Report;
import com.ftn.sbnz.service.core.PaginatedResponse;
import com.ftn.sbnz.service.dto.historyItem.HistoryItemViewDto;
import com.ftn.sbnz.service.dto.historyItem.ReportDto;
import com.ftn.sbnz.service.mapper.HistoryItemMapper;
import com.ftn.sbnz.service.security.AuthenticationService;
import com.ftn.sbnz.service.service.HistoryItemService;
import com.ftn.sbnz.service.service.StudentService;
import com.ftn.sbnz.service.util.LongGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/students/{studentId}/histories")
public class HistoryItemController {
    private final HistoryItemService service;
    private final HistoryItemMapper mapper;
    private final StudentService studentService;
    private final AuthenticationService authenticationService;
    private final LongGenerator reportId;

    public HistoryItemController(HistoryItemService service, HistoryItemMapper mapper, StudentService studentService, AuthenticationService authenticationService, LongGenerator reportId) {
        this.service = service;
        this.mapper = mapper;
        this.studentService = studentService;
        this.authenticationService = authenticationService;
        this.reportId = reportId;
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<HistoryItemViewDto> addHistory(@PathVariable Long studentId, @Valid @RequestBody ReportDto reportDto) {
        Report report = mapper.toModel(reportId.next(), reportDto);
        HistoryItem newest = service.reportStudent(studentId, report);
        HistoryItemViewDto newestDto = mapper.toViewDto(newest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newestDto);
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
