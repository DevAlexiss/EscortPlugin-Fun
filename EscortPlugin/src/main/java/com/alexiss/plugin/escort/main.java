package com.alexiss.plugin.escort;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.commands.CommandHandler;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.HabboPlugin;
import com.eu.habbo.plugin.events.emulator.EmulatorLoadedEvent;
import com.alexiss.plugin.escort.commands.EscortCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class main extends HabboPlugin implements EventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(main.class);

    @Override
    public void onEnable() throws Exception {
        Emulator.getPluginManager().registerEvents(this, this);
        if(Emulator.isReady && !Emulator.isShuttingDown) {
            this.onEmulatorLoadedEvent(null);
        }
    }

    @Override
    public void onDisable() throws Exception {

    }

    @Override
    public boolean hasPermission(Habbo habbo, String s) {
        return false;
    }

    @EventHandler
    public void onEmulatorLoadedEvent (EmulatorLoadedEvent e) {
        CommandHandler.addCommand(new EscortCommand());
        LOGGER.info("PLUGIN - EscortPlugin has started!");
    }
}
