package io.pivotal.pal.tracker.timesheets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TimesheetsApp.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TimesheetsAppTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testGetTimeEntriesByUserId() {
        String response = restTemplate.getForObject("/time-entries?userId=0", String.class);

        assertThat(response).isEqualTo("[]");
    }
}
