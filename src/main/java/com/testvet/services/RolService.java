package com.testvet.services;

import com.testvet.model.Rol;
import com.testvet.repository.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {
    @Autowired
    private IRolRepository repoRol;

    public List<Rol> listarRoles(){
        return repoRol.findAll();
    }

}
