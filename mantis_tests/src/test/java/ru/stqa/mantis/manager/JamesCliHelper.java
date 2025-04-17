package ru.stqa.mantis.manager;

import org.openqa.selenium.io.CircularOutputStream;
import org.openqa.selenium.os.CommandLine;
//import org.apache.commons.exec.CommandLine;

public class JamesCliHelper extends HelperBase {

    public JamesCliHelper(ApplicationManager manager) {
        super(manager);
    }

    public void addUser(String email, String password) {

        /*
        CommandLine cmd = new CommandLine(String.format("java -cp \"james-server-jpa-app.lib/*\" " +
                "org.apache.james.cli.ServerCmd AddUser %s %s", email, password));
        cmd.getExecutable();
        //cmd.wait(60);

         */


        CommandLine cmd = new CommandLine("java", "-cp", "\"james-server-jpa-app.lib/*\"",
                "org.apache.james.cli.ServerCmd", "AddUser", email, password);

        cmd.setWorkingDirectory(manager.property("james.workingDir"));
        CircularOutputStream out = new CircularOutputStream(); //переменная, куда будет записан результат выполнения - создания нового пользователя на сервере
        cmd.copyOutputTo(out);
        cmd.execute();
        cmd.waitFor();
        System.out.println(out);




    }
}
