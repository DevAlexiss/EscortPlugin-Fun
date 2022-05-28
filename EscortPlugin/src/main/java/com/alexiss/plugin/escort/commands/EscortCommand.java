package com.alexiss.plugin.escort.commands;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.bots.Bot;
import com.eu.habbo.habbohotel.commands.Command;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.habbohotel.rooms.*;
import com.eu.habbo.habbohotel.users.DanceType;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.habbohotel.users.HabboGender;
import com.eu.habbo.messages.outgoing.rooms.users.*;
import com.alexiss.plugin.escort.EscortBot;

public class EscortCommand extends Command {
    private int botCount = 0;
    private final String[] looks;

    public EscortCommand() {
        super("cmd_escorts", Emulator.getTexts().getValue("commands.keys.escorts", "escorts").split(";"));
        looks = new String[]{
                "hd-205-1.ch-884-72.lg-3198-72.hr-515-32.he-1610-62"
        };
    }

    @Override
    public boolean handle(GameClient gameClient, String[] params) {
        if (params.length >= 2) {
            Room room = gameClient.getHabbo().getRoomUnit().getRoom();

            if(room == null) return false;

            int amount;
            try {
                amount = Integer.parseInt(params[1]);
            } catch (Exception e) {
                return false;
            }

            this.deployBots(amount, room, gameClient.getHabbo());
            botCount = botCount + amount;
            return true;
        }
        return false;
    }

    public void deployBots(int amount, Room room, Habbo owner) {
        for(int i = 0; i < amount; i++) {
            EscortBot bot = new EscortBot((Integer.MAX_VALUE - this.botCount) - i, owner.getHabboInfo().getUsername() + "escort" + i,
                    "Escort", this.getRandomLook(), HabboGender.M, owner.getHabboInfo().getId(), owner.getHabboInfo().getUsername());
            RoomTile location = room.getRandomWalkableTile();
            RoomUnit roomUnit = new RoomUnit();
            roomUnit.setRotation(RoomUserRotation.SOUTH);
            roomUnit.setLocation(location);
            //double stackHeight = room.getStackHeight(location.x, location.y, false);
            //roomUnit.setPreviousLocationZ(stackHeight);
            //roomUnit.setZ(stackHeight);

            roomUnit.setPathFinderRoom(room);
            roomUnit.setRoomUnitType(RoomUnitType.BOT);
            roomUnit.setCanWalk(room.isAllowBotsWalk());
            bot.setRoomUnit(roomUnit);
            bot.setRoom(room);
            room.addBot(bot);
            room.sendComposer(new RoomUsersComposer(bot).compose());
            room.sendComposer(new RoomUserStatusComposer(bot.getRoomUnit()).compose());
            bot.cycle(true);
            bot.setCanWalk(true);
        }
    }

    public String getRandomLook() {
        int random = Emulator.getRandom().nextInt(this.looks.length);
        return this.looks[random];
    }
}
