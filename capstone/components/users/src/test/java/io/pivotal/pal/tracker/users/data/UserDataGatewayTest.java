package io.pivotal.pal.tracker.users.data;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDataGatewayTest {

    private UserDataGateway gateway = new UserDataGateway();

    @Test
    public void testCreate() {
        UserRecord createdUser = gateway.create("aUser");

        assertThat(createdUser.id).isGreaterThan(0);
        assertThat(createdUser.name).isEqualTo("aUser");

        UserRecord found = gateway.find(createdUser.id);

        assertThat(found.id).isEqualTo(createdUser.id);
        assertThat(found.name).isEqualTo(createdUser.name);
    }

    @Test
    public void testFind_WhenNotFound() {
        assertThat(gateway.find(42347L)).isNull();
    }
}
