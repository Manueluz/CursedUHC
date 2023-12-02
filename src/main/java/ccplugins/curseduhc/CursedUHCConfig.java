package ccplugins.curseduhc;

import java.util.*;

import ccplugins.curseduhc.Service.Service;

public class CursedUHCConfig {
    private final List<Service> services = new ArrayList<>();
    
    public CursedUHCConfig addService(Service service){
        services.add(service);
        return this;
    }
    
    public CursedUHCConfig init() {
        services.forEach(service -> {
            CursedUHC.logger.info("Loading " + service.getClass().getSimpleName() + "...");
            service.init(this);
        });
        return this;
    }
    
    public CursedUHCConfig stop() {
        services.forEach((service) -> {
            CursedUHC.logger.info("Un-Loading " + service.getClass().getSimpleName() + "...");
            service.stop();
        });
        return this;
    }
    
    //It is checked by the filter, compiler does not realize it
    @SuppressWarnings("unchecked")   
    public <T extends Service> T getByClass(Class<T> clazz){
        return services
            .stream()
            .filter(h->h.getClass().equals(clazz))
            .map(h -> (T) h)
            .findFirst()
            .orElseThrow(()-> new RuntimeException("Service not found!"));
    }
}