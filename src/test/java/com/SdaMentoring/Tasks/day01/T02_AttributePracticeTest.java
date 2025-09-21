package com.SdaMentoring.Tasks.day01;

import org.testng.annotations.Test;

public class T02_AttributePracticeTest {


    /*
    Task 2: TestNG Annotation Attributes Practice
Objective: Implement various TestNG annotation attributes
Requirements:
Create a class called AttributePracticeTest
Create 5 test methods with different priorities (1, 3, 2, 5, 4)
Create one test method with enabled = false
Create a test method with timeout of 3 seconds that includes Thread.sleep(2000)
Create a test method with timeout of 1 second that includes Thread.sleep(2000)
6. Add meaningful descriptions to all test methods
Create test methods with groups: "smoke","regression","api"
     */

    @Test(priority = 1,description = "Highest priority")
    void Test01(){
        System.out.println(" Test01");
    }

    @Test(priority = 3,description = "Second test",groups = "smoke")
    void Test02(){
        System.out.println(" Test02");

    }

    @Test(priority = 2 ,description = "Second priority")
    void Test03(){
        System.out.println(" Test03");
    }

    @Test(priority = 5,description = "the lowest priority",groups = "api")
    void Test04(){
        System.out.println(" Test04");
    }

    @Test(priority = 4,description = "fifth test with priority 4")
    void Test05(){
        System.out.println(" Test05");
    }

    @Test(enabled = false, description = "the test is ignored",groups = "regression")
    void Test06(){
        System.out.println(" Test06");
    }

    @Test(timeOut = 3000,description = "Seventh test with TimeOut for 3 sec")
    void Test07() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println(" Test07");
    }

    @Test(timeOut = 1000,description = "last test with TimeOut for 1 sec")
    void Test08() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println(" Test08");
    }


}
