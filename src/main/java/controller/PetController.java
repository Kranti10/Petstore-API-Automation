package controller;

import dataModel.Pet;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import responseDataModal.PetPojoResponse;

import java.io.PrintStream;

import static io.restassured.RestAssured.given;
import static utils.Properties.baseUri;


//petstore.swagger.io/v2/pet
public class PetController {
    public static String PET_ENDPOINT = baseUri + "/pet";
    private RequestSpecification requestSpecification;
    // PrintStream printStream;

    public PetController() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(baseUri);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();
    }

    // post method:- to add a new pet to the store

    public Response addNewPet(Pet pet, PrintStream printStream) {
        return given(requestSpecification).filter(new RequestLoggingFilter(printStream)).body(pet).post(PET_ENDPOINT);

    }


    // put method:-to update pet to the store
    public Response updatePet(Pet pet, PrintStream captor) {
        return given(requestSpecification).filter(new RequestLoggingFilter(captor)).body(pet).put(PET_ENDPOINT);
    }

    // get method:-to find pet to the store
    public PetPojoResponse findPet(Pet pet) {
        return given(requestSpecification).pathParam("petId", pet.getId()).get(PET_ENDPOINT + "/{petId}").as(PetPojoResponse.class);
    }

    //  delete method:-to delete pet from the store
    public Response deletePet(Pet pet) {
        return given(requestSpecification).pathParam("petId", pet.getId()).delete(PET_ENDPOINT);
    }


}
