package com.integrys.backend;

import com.integrys.backend.entities.Role;
import com.integrys.backend.entities.Setting;
import com.integrys.backend.entities.User;
import com.integrys.backend.entities.enums.Authority;
import com.integrys.backend.repositories.RoleRepository;
import com.integrys.backend.repositories.SettingRepository;
import com.integrys.backend.repositories.UserRepository;
import com.integrys.backend.services.StorageService;

import java.awt.image.BufferedImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

@SpringBootApplication
@EnableJpaRepositories
public class IntegrysBackendSettingsApplication extends SpringBootServletInitializer implements ApplicationRunner {

    private final StorageService storageService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SettingRepository settingRepository;


    @Autowired
    public IntegrysBackendSettingsApplication(StorageService storageService, UserRepository userRepository, RoleRepository roleRepository, SettingRepository settingRepository) {
        this.storageService = storageService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.settingRepository = settingRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(IntegrysBackendSettingsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //Init storage
        this.storageService.init();
        // Create Super-Admin
        if (!userRepository.existsByUsernameOrEmail("super-admin","super-admin")) {
            Role role = roleRepository.findByName(Authority.SUPER_ADMIN);
            if(role == null){
                role = new Role(Authority.SUPER_ADMIN);
                roleRepository.save(role);
            }
            userRepository.save(new User("super-admin", "12345678", "admin@integrys.com", "+237699123456", "Administrator", "Super",role));
        }
        
        // Create App setting
        
        if (settingRepository.count()==0) {
        	Setting initSetting = new Setting(); initSetting.setNom("Integrys");
        	 settingRepository.save(initSetting); 
        }
        
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IntegrysBackendSettingsApplication.class);
    }
    @Bean
    HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }
}
