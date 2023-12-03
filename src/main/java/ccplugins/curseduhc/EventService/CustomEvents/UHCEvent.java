package ccplugins.curseduhc.EventService.CustomEvents;


public interface UHCEvent {
    void start();
    void update();
    void end();
    String getName();
    int getDuration();
}
