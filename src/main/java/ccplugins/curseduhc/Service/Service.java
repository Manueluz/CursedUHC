package ccplugins.curseduhc.Service;

import ccplugins.curseduhc.CursedUHCConfig;

public interface Service {
    void init(CursedUHCConfig config);
    void stop();
}