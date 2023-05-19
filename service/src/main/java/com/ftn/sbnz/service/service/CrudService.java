package com.ftn.sbnz.service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T> {
    T add(T newT);

    Page<T> getAll(Pageable pageable);

    T getById(Long id);

    T update(Long id, T changes);

    void delete(Long id);
}
