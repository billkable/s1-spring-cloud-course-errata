package io.pivotal.pal.tracker.registration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegistrationApp.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrationAppTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testGetEndpoints() {
        assertThat(restTemplate.getForObject("/accounts?ownerId=0", String.class)).isEqualTo("[]");
        assertThat(restTemplate.getForObject("/projects?accountId=0", String.class)).isEqualTo("[]");
        assertThat(restTemplate.getForObject("/projects/0", String.class)).isEqualTo(null);
        assertThat(restTemplate.getForObject("/users/0", String.class)).isEqualTo(null);
    }
}
