package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.IssueData;

public class IssueCreationTests extends TestBase {



    @Test
    void canCreateIssue() {
        app.rest().createIssue(new IssueData()
                .withSummary(CommonFunctions.randomString(10))
                .withDescription(CommonFunctions.randomString(50))
                .withProject(1L)); //Id - либо фиксированное значение, вынесенное в конфигурационный файл
        //или получить через web/rest- интерфейсы данные о существующих проектах, выбрать какой-то из них и использовать его id здесь

    }


}
