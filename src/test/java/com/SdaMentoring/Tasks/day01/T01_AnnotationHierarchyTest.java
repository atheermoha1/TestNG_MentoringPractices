package com.SdaMentoring.Tasks.day01;

import org.testng.annotations.*;

public class T01_AnnotationHierarchyTest {
    /*
    AnnotationHierarchyTest
Task 1: Basic TestNG Setup and Annotations
Objective: Create a TestNG class demonstrating annotation hierarchy
Requirements:
Create a TestNG class called AnnotationHierarchyTest
Implement all TestNG annotations (@BeforeSuite, @BeforeTest, @BeforeClass, @BeforeMethod,
@Test, @AfterMethod, @AfterClass, @AfterTest, @AfterSuite)
Add print statements in each method to observe execution order
Create 2 test methods
Run the test and analyze console output
     */

    @BeforeSuite
    void BeforeSuite(){
        System.out.println("BeforeSuite");
    }
    @BeforeTest
    void BeforeTest(){
        System.out.println("BeforeSuite");

    }
    @BeforeClass
     void BeforeClass(){
        System.out.println("BeforeClass");

    }
    @BeforeMethod
    void BeforeMethod(){
        System.out.println("BeforeMethod");

    }


    @Test
    void Test01(){
        System.out.println(" Test01");
    }

    @Test
    void Test02(){
        System.out.println(" Test02");
    }

    @AfterMethod
    void AfterMethod(){
        System.out.println("AfterMethod");

    }
    @AfterClass
    void AfterClass(){
        System.out.println("AfterClass");

    }

    @AfterTest
    void AfterTest(){
        System.out.println("AfterTest");

    }

    @AfterSuite
    void AfterSuite(){
        System.out.println("AfterSuite");

    }


}
