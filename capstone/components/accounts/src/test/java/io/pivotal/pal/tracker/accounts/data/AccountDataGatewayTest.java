package io.pivotal.pal.tracker.accounts.data;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountDataGatewayTest {

    private AccountDataGateway gateway = new AccountDataGateway();

    @Test
    public void testCreate() {
        AccountRecord created = gateway.create(12L, "anAccount");
        gateway.create(12L, "anotherAccount");

        assertThat(created.id).isNotNull();
        assertThat(created.name).isEqualTo("anAccount");
        assertThat(created.ownerId).isEqualTo(12);

        List<AccountRecord> all = gateway.findAllByOwnerId(12L);

        assertThat(all.size()).isEqualTo(2);

        AccountRecord persisted = all.get(0);

        assertThat(persisted.name).isEqualTo("anAccount");
        assertThat(persisted.ownerId).isEqualTo(12L);
    }

    @Test
    public void testNoneByOwnerId() {
        List<AccountRecord> all = gateway.findAllByOwnerId(12L);

        assertThat(all.size()).isEqualTo(0);
    }
}
