package io.pivotal.pal.tracker.users.data;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserDataGateway {

    private final HashMap<Long, UserRecord> db = new HashMap<>();

    public UserRecord create(String name) {

        long id = 1L;

        UserRecord created = new UserRecord(id, name);

        db.put(id, created);

        return created;
    }

    public UserRecord find(long id) {
        return db.get(id);

    }

}
