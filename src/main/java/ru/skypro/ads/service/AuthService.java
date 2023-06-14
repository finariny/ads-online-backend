package ru.skypro.ads.service;

import ru.skypro.ads.dto.RegisterReqDto;
import ru.skypro.ads.dto.RoleDto;

public interface AuthService {
    boolean login(String userName, String password);
//    boolean register(RegisterReqDto registerReqDto, RoleDto roleDto);

    boolean register(RegisterReqDto registerReq);
}
