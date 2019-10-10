package io.pivotal.pal.tracker.accounts.data;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountDataGateway {

    private long currentId = 0;
    private final Map<Long, List<AccountRecord>> db = new HashMap<>();

    public AccountRecord create(long ownerId, String name) {
        AccountRecord created = AccountRecord.accountRecordBuilder().id(++currentId).name(name).ownerId(ownerId).build();

        List<AccountRecord> existingRecords = db.getOrDefault(ownerId, new ArrayList<>());
        existingRecords.add(created);
        db.put(ownerId, existingRecords);

        return created;
    }

    public List<AccountRecord> findAllByOwnerId(long ownerId) {
        return db.getOrDefault(ownerId, new ArrayList<>());
    }
}
