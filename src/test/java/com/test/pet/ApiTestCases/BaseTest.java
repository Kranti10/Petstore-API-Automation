package com.test.pet.ApiTestCases;


import io.restassured.RestAssured;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.Properties;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseTest {

    protected StringWriter writer;
    protected PrintStream printStream;



    //LoginPage loginPage;
    @BeforeSuite
    public void setUpSuite() {

    }



    @AfterSuite
    public void afterSuite() throws IOException {


    }

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = Properties.baseUri;
        writer = new StringWriter();
        printStream = new PrintStream(new WriterOutputStream(writer), true);
    }


    protected void formatAPIAndLogInReport(String content) {

        String prettyPrint = content.replace("\n", "<br>");


    }


    /*
     * Read the json file and convert to String
     *
     * @param  : filepath
     */
    public String generateStringFromResource(String path) throws IOException {
        String temp = "";
        try {
            temp = new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;

    }

    public void
    writeRequestAndResponseInReport(String request, String response) {

        System.out.println("---- Request ---");
        formatAPIAndLogInReport(request);
        System.out.println("---- Response ---");
        formatAPIAndLogInReport(response);
    }

}
