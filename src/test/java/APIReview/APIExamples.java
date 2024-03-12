package APIReview;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import utils.APIConstants;
import utils.APIPayloadConstants;

import static io.restassured.RestAssured.given;

public class APIExamples {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTY5ODg5NjUsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NzAzMjE2NSwidXNlcklkIjoiODMifQ.kt_CJTjr5HXnsrZanO8CA0Mz_gXClxdIPQA3nh8oywE";

    @Test
    public void createAnEmployee() {
        RequestSpecification request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.HEADER_CONTENT_TYPE_VALUE).header(APIConstants.HEADER_AUTHORIZATION, token).body(APIPayloadConstants.createEmployeePayload());
        Response response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);

        //First create json element for response
        JsonElement jsonElement = new JsonParser().parse(response.asString());
        //System.out.println(jsonElement);

        //Create Json object for what's inside
        JsonObject jsonObject  =jsonElement.getAsJsonObject();
        System.out.println(jsonObject);

        //Getting inner element as key,value map
        JsonElement messageElement =jsonObject.get("Message");
        System.out.println(messageElement);

        JsonElement employeeElement =jsonObject.get("Employee");
        System.out.println(employeeElement);

        //Getting more inner element as key,value map
        JsonObject jsonObjectInner=  employeeElement.getAsJsonObject();

        JsonElement empIdElement = jsonObjectInner.get("employee_id");
        System.out.println(empIdElement);

        JsonElement firstNameElement =jsonObjectInner.get("emp_firstname");
        System.out.println(firstNameElement);

        Assert.assertEquals(employeeElement,jsonObjectInner);
    }
}
