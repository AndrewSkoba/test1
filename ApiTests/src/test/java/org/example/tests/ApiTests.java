package org.example.tests;

import io.qameta.allure.*;
import org.example.models.body.Data;
import org.example.models.body.Root;
import org.example.models.body.Support;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.restassured.RestAssured.given;

public class ApiTests extends BaseTest {

    @Step("Run Firt test")
    @Description("First API within smoke suite")
    @Epic("First Epic")
    @Story("First Stroy")
    @Feature("First Feature")
    @Test
    public void verifyBodyOfPertsStatusEndpoint() {
        Root actualResult = executeCall();
        Root expectedData = buildTestData();

        addAttachment("tag.json");
        Assert.assertEquals(actualResult, expectedData, "Data are not equals");
    }

    @Step("Run Firt test")
    @Description("First API within smoke suite")
    @Epic("First Epic")
    @Story("First Stroy2")
    @Feature("First Feature")
    @Severity(BLOCKER)
    @Issue("BUG-1234")
    @TmsLinks(value = {@TmsLink("TC-321"), @TmsLink("TC-322")})
    @Flaky
    @Link(name = "Jira", url = "https://www.atlassian.com/?&aceid=&adposition=&adgroup=99178949694&campaign=9869842064&creative=431933496949&device=c&keyword=atlassian&matchtype=e&network=g&placement=&ds_kids=p53276225429&ds_e=GOOGLE&ds_eid=700000001530700&ds_e1=GOOGLE&utm_medium=paid-search&gad_source=1&gclid=Cj0KCQiAj_CrBhD-ARIsAIiMxT9lSfRU0Q5eGM3ZFzqjnBgNZI5mseZ_YYmob97zhFtvs8Cx6buQ3_caAiBjEALw_wcB&gclsrc=aw.ds")
    @Test
    public void negativeTest() {

        Root actualResult = executeCall();
        Root expectedData = buildInvalidTestData();

        addAttachment("screen.png");

        Assert.assertEquals(actualResult, expectedData, "Data are not equals");
    }

    @Step("Execute GET request")
    public Root executeCall() {
        return given()
                .spec(getRequestSpecification("https://reqres.in/"))
                .when()
                .get("api/users/2")
                .then()
                .extract()
                .as(Root.class);
    }

    @Step("Build expected Data for Root obj")
    public Root buildTestData() {
        return Root.builder()
                .data(Data.builder()
                        .id(2)
                        .email("janet.weaver@reqres.in")
                        .first_name("Janet")
                        .last_name("Weaver")
                        .avatar("https://reqres.in/img/faces/2-image.jpg")
                        .build())
                .support(Support.builder()
                        .url("https://reqres.in/#support-heading")
                        .text("To keep ReqRes free, contributions towards server costs are appreciated!")
                        .build())
                .build();
    }

    @Step("Build expected Data for Root obj")
    public Root buildInvalidTestData() {
        return Root.builder()
                .data(Data.builder()
                        .id(3)
                        .email("janet.weaver@reqres.in")
                        .first_name("Janet")
                        .last_name("Weaver")
                        .avatar("https://reqres.in/img/faces/2-image.jpg")
                        .build())
                .support(Support.builder()
                        .url("https://reqres.in/#support-heading")
                        .text("To keep ReqRes free, contributions towards server costs are appreciated!")
                        .build())
                .build();
    }

    @Attachment
    public byte[] addAttachment(String fileName) {
        try {
            return Files.readAllBytes(Paths.get("src/test/resources", fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
