package ru.stqa.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.CreateIssueResponse;
import io.swagger.client.model.Identifier;
import io.swagger.client.model.Issue;
import ru.stqa.mantis.model.IssueData;

public class RestApiHelper extends HelperBase {

    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        //Инициализация
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        //м-д устанавливает ключ авторизации
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));//ключ-токен создается в самом приложении mantis


    }


    public void createIssue(IssueData issueData) {

        //готовили данные - конвертировали объект типа IssueData в типа Issue
        Issue issue = new Issue(); // Issue | The issue to add.
        //конвертируем модельный объект в сгенерированный автоматически
        issue.setSummary(issueData.summary());
        issue.setDescription(issueData.description());

        var projectId = new Identifier();
        projectId.setId(issueData.project());
        issue.setProject(projectId);

        var categoryId = new Identifier();
        categoryId.setId(issueData.category());
        issue.setProject(categoryId);


        //использовали объект, чтобы осуществить вызов через удаленный программный интерфейс
        IssuesApi apiInstance = new IssuesApi();
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
            new RuntimeException(e);
        }
    }
}
