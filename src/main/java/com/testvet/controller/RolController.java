package com.testvet.controller;

import com.testvet.model.Rol;
import com.testvet.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolService service;

    @GetMapping
    public ResponseEntity<?> listarRoles(){
        List<Rol> roles = service.listarRoles();
        return ResponseEntity.ok(roles);
    }
}