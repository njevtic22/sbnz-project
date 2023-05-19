package com.ftn.sbnz.service.mapper;

import com.ftn.sbnz.model.model.Admin;
import com.ftn.sbnz.service.dto.admin.AddAdminDto;
import com.ftn.sbnz.service.dto.admin.AdminViewDto;
import com.ftn.sbnz.service.dto.admin.UpdateAdminDto;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    public Admin toModel(AddAdminDto addAdminDto) {
        return new Admin(
                addAdminDto.getName(),
                addAdminDto.getSurname(),
                addAdminDto.getBirthDate(),
                addAdminDto.getJmbg(),
                addAdminDto.getEmail(),
                addAdminDto.getUsername(),
                addAdminDto.getPassword(),
                false,
                null
        );
    }

    public Admin toModel(UpdateAdminDto updateAdminDto) {
        return new Admin(
                updateAdminDto.getName(),
                updateAdminDto.getSurname(),
                updateAdminDto.getBirthDate(),
                updateAdminDto.getJmbg(),
                updateAdminDto.getEmail(),
                updateAdminDto.getUsername(),
                "",
                false,
                null
        );
    }

    public AdminViewDto toViewDto(Admin admin) {
        return new AdminViewDto(
                admin.getId(),
                admin.getName(),
                admin.getSurname(),
                admin.getBirthDate(),
                admin.getJmbg(),
                admin.getEmail(),
                admin.getUsername(),
                admin.getRole().getName()
        );
    }
}
