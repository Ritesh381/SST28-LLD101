import java.util.*;

public class OnboardingService {
    private final StudentRepository db;
    private Validator validator;
    private Parser parser;
    private OnboardingPrinter printer;

    public OnboardingService(StudentRepository db) { 
        this.db = db; 
        this.validator = new Validator();
        this.parser = new Parser();
        this.printer = new OnboardingPrinter();
    }

    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {
        printer.printInput(raw);
        parser.parse(raw);

        String name = parser.getField("name");
        String email = parser.getField("email");
        String phone = parser.getField("phone");
        String program = parser.getField("program");

        // validation inline, printing inline
        List<String> errors = new ArrayList<>();
        if (validator.checkName(name)) errors.add("name is required");
        if (validator.checkEmail(email)) errors.add("email is invalid");
        if (validator.checkPhone(phone)) errors.add("phone is invalid");
        if (validator.checkProgram(program)) errors.add("program is invalid");

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        db.save(rec);
        printer.printSuccess(id, db.count(), rec);
    }
}
