package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.School;

public interface SchoolService {
    School getById(/*Long id*/);

    School save(School school);
}
