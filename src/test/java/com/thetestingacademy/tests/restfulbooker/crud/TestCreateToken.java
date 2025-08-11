package com.thetestingacademy.tests.restfulbooker.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestCreateToken extends BaseTest{

    @Test(groups = "reg", priority = 1)
    @Owner("Akash")
    @Description("TC#1 - Create token and Verify")
    public void testTokenPost() {

        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification).when()
                .body(payloadManager.setAuthPayload()).post();

        String token = payloadManager.getTokenFromJSON(response.asString());
        System.out.println(token);

        // Validation of the request.
        assertActions.verifyStringKeyNotNull(token);
    }


    @Test(groups = "reg", priority = 1)
    @Owner("Akash")
    @Description("TC#2 - Create invalid token")
    public void testTokenPostNegative() {

        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification).when()
                .body("{}").post();

        String invalidreason = payloadManager.getInvalidResponse(response.asString());
        System.out.println(invalidreason);

        // Validation of the request.
        assertActions.verifyStringKey(invalidreason, "Bad credentials");
    }
}
