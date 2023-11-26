package ccplugins.curseduhc.Handlers;

import ccplugins.curseduhc.CursedUHCConfig;

public interface Handler {
    void init(CursedUHCConfig config);
    void stop();
}