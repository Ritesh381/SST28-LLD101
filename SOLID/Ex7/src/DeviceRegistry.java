import java.util.*;

public class DeviceRegistry {
    private final java.util.List<SmartClassroomDevice> devices = new ArrayList<>();

    public void add(SmartClassroomDevice d) { devices.add(d); }

    public SmartClassroomDevice getFirstOfType(String simpleName) {
        for (SmartClassroomDevice d : devices) {
            if (d.getClass().getSimpleName().equals(simpleName)) return d;
        }
        throw new IllegalStateException("Missing: " + simpleName);
    }

    public <T> T getFirstOfType(String simpleName, Class<T> capability) {
        for (SmartClassroomDevice d : devices) {
            if (d.getClass().getSimpleName().equals(simpleName) && capability.isInstance(d)) {
                return capability.cast(d);
            }
        }
        throw new IllegalStateException("Missing: " + simpleName + " with capability " + capability.getSimpleName());
    }
}
