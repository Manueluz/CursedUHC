package ccplugins.curseduhc;

import ccplugins.curseduhc.Handlers.Handler;

import java.util.*;

public class CursedUHCConfig {
    private final List<Handler> handlers = new ArrayList<>();
    public CursedUHCConfig addHandler(Handler handler){
        handlers.add(handler);
        return this;
    }
    
    public CursedUHCConfig init() {
        handlers.forEach(handler -> handler.init(this));
        return this;
    }
    
    public CursedUHCConfig stop() {
        handlers.forEach(Handler::stop);
        return this;
    }
    
    //It is checked by the filter, compiler does not realize it
    @SuppressWarnings("unchecked")   
    public <T extends Handler> T getByClass(Class<T> clazz){
        return handlers
            .stream()
            .filter(h->h.getClass().equals(clazz))
            .map(h -> (T) h)
            .findFirst()
            .orElseThrow(()-> new RuntimeException("Handler not found!"));
    }
}