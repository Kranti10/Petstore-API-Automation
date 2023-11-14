package com.test.pet.ApiTestCases;

import controller.PetController;
import dataModel.Category;
import dataModel.Pet;
import dataModel.Status;
import dataModel.Tag;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import responseDataModal.PetPojoResponse;

import java.util.Collections;

/**
 * test class for pet API end point
 */
public class PetTests extends BaseTest {
    private static final String PHOTO_URL = "https://www.tesco.ie/groceries/MarketingContent/Sites/Retail/superstore/Online/P/i/departments/2016/Pets/1BC.jpg";
    PetController petController;
    Pet pet = new Pet.Builder()
            .withId(RandomStringUtils.randomNumeric(5))
            .withName("My pet")
            .withPhotoUrls(Collections.singletonList(PHOTO_URL))
            .withStatus(Status.available)
            .withTags(Collections.singletonList(new Tag(1, "golden-retriever")))
            .inCategory(new Category(1, "dogs")).build();



   /* Pet pet = new Pet(RandomStringUtils.randomNumeric(5),"My pet",Collections.singletonList(PHOTO_URL),
            Status.available,Collections.singletonList(new Tag(1, "golden-retriever")),
            new Category(1, "dogs"));*/

    @BeforeClass
    public void beforeClass() {
        petController = new PetController();
    }

    @Test(priority = 0)
    public void addNewPet() {
        Response response = petController.addNewPet(pet, printStream);
        PetPojoResponse petResponse = response.as(PetPojoResponse.class);
        Assert.assertEquals(petResponse.getName(), pet.getName());
       // writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());

    }

    @Test(priority = 1)
    public void verifyNewPet() {
        PetPojoResponse petResponse = petController.findPet(pet);
        Assert.assertEquals(petResponse.getName(), pet.getName());
    }

    @Test(priority = 2)
    public void updatePet() {
        pet.setName("New name for my pet");
        pet.setStatus(Status.pending);
        Response response = petController.updatePet(pet, printStream);
        PetPojoResponse petResponse = response.as(PetPojoResponse.class);
        Assert.assertEquals(petResponse.getName(), pet.getName());
        Assert.assertEquals(petResponse.getStatus(), pet.getStatus().toString());
        writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());
    }

    @Test(priority = 3)
    public void verifyUpdatedPet() {
        PetPojoResponse petResponse = petController.findPet(pet);
        Assert.assertEquals(petResponse.getName(), pet.getName());
    }

    @Test(priority = 4)
    public void verifyDeletePet() {
        Response response= petController.deletePet(pet);
        Assert.assertEquals(200, response.getStatusCode());
    }

}
