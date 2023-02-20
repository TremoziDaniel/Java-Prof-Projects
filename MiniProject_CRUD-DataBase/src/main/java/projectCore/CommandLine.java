package projectCore;

import java.util.Scanner;

public class CommandLine {
    public void exec() {
        Database db = new Database(DBInit.read());
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nCRUD-DB-v0.3");
        while(true) {
            System.out.print("# ");
            String cmd = scanner.next();
            switch(cmd.toLowerCase().charAt(0)) {
                case 'c' :
                    db.create();
                    break;
                case 'r':
                    db.read();
                    break;
                case 'u':
                    db.update();
                    break;
                case 'd':
                    db.delete();
                    break;
                case 'f':
                    db.find();
                    break;
                case 'p':
                    db.positions();
                    break;
                case 's':
                    db.sort();
                    break;
                case 'x':
                    DBInit.save(db.getEmployees());
                    System.out.println("Database has been successfully saved.");
                    System.out.println("Finish / Exit of program");
                    return;
                default:
                    System.out.println("List of command: c[reate], r[ead], u[pdate], d[elete], f[ind], p[osition], s[ort], e[x]it");
            }
        }
    }
}
