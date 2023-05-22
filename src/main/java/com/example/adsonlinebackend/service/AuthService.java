package com.example.adsonlinebackend.service;

import com.example.adsonlinebackend.dto.RegisterReq;
import com.example.adsonlinebackend.dto.Role;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReq registerReq, Role role);
}
