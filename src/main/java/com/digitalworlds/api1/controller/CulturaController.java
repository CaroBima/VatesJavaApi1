package com.digitalworlds.api1.controller;

import com.digitalworlds.api1.dto.ProgramaDTO;
import com.digitalworlds.api1.services.ICulturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CulturaController {

    @Autowired
    ICulturaService culturaService;

   /*@Autowired
    public CulturaController(CulturaService CulturaService) {
        this.CulturaService = CulturaService;
    }*/


    @GetMapping("/programas")
    public ResponseEntity<List<ProgramaDTO>> getExternalPrograms() {
        return ResponseEntity.ok( culturaService.getCulturaData());
   }
}
