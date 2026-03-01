public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        CanBeTurnOn pjPower = reg.getFirstOfType("Projector", CanBeTurnOn.class);
        Connectable pjInput = reg.getFirstOfType("Projector", Connectable.class);
        pjPower.powerOn();
        pjInput.connectInput("HDMI-1");

        Brightness lights = reg.getFirstOfType("LightsPanel", Brightness.class);
        lights.setBrightness(60);

        Temperature ac = reg.getFirstOfType("AirConditioner", Temperature.class);
        ac.setTemperatureC(24);

        Scannable scan = reg.getFirstOfType("AttendanceScanner", Scannable.class);
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        reg.getFirstOfType("Projector", CanBeTurnOff.class).powerOff();
        reg.getFirstOfType("LightsPanel", CanBeTurnOff.class).powerOff();
        reg.getFirstOfType("AirConditioner", CanBeTurnOff.class).powerOff();
    }
}
