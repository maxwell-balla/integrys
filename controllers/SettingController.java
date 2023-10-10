package com.integrys.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.integrys.backend.entities.Setting;
import com.integrys.backend.repositories.SettingRepository;
import java.util.List;
@RestController
@CrossOrigin
@Transactional
public class SettingController {
    private final SettingRepository settingRepository;

    @Autowired
    public SettingController(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @GetMapping("/setting")
    public Setting getSettings() {
        List<Setting> settings = this.settingRepository.findAll();
        if(settings.size() > 0){
            return settings.get(0);
        }
        return null;
    }

    @PutMapping("/setting/{id}")
    public ResponseEntity<Object> updateSetting(@RequestBody Setting setting, @PathVariable(name = "id") Long id) {
        if (this.settingRepository.existsById(id)) {
            //Setting existingSetting = this.settingRepository.findById(id).get();
            setting.setId(id);
            return ResponseEntity.ok().body(this.settingRepository.save(setting));
        } else {
            return ResponseEntity.badRequest().body("Impossible de modifier, l'id du parametre est introuvable!");

        }

    }
}