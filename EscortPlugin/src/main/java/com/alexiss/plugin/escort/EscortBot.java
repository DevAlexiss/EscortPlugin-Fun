package com.alexiss.plugin.escort;

import com.eu.habbo.habbohotel.bots.Bot;
import com.eu.habbo.habbohotel.users.HabboGender;

public class EscortBot extends Bot {
    public EscortBot(int id, String name, String motto, String figure, HabboGender gender, int ownerId, String ownerName) {
        super(id, name, motto, figure, gender, ownerId, ownerName);
    }

    @Override
    public void run() {
        // do nothing, we dont wanna save anything to db
    }
}
