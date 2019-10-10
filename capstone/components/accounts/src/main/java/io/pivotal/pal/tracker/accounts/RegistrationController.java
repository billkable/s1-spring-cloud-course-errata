package io.pivotal.pal.tracker.accounts;

import io.pivotal.pal.tracker.users.UserInfo;
import io.pivotal.pal.tracker.users.data.UserRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    private final RegistrationService service;

    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserInfo> create(@RequestBody RegistrationForm form) {
        UserRecord record = service.createUserWithAccount(form.name);
        return new ResponseEntity<>(new UserInfo(record.id, record.name, "registration info"),
                HttpStatus.CREATED);
    }
}
