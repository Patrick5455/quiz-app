package com.cova.quizapp.serviceimpl;

import com.cova.quizapp.data.AppUserRepository;
import com.cova.quizapp.service.IAppUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class AppUserServiceImpl implements IAppUserService {

    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }


}
