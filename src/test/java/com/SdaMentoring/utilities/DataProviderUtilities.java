package com.SdaMentoring.utilities;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public class DataProviderUtilities {

    @DataProvider
    public Object[][] getRecords() {
        return new Object[][]{
                {"johndoe", "123", "Comments from John", "Drop Down Item 6"},
                {"marystar", "767", "Comments from Mary", "Drop Down Item 2"},
                {"tomhawk", "889", "Comments from Tom", "Drop Down Item 1"},
                {"timlee", "098", "Comments from Tim", "Drop Down Item 5"},
                {"kenjoe", "567", "Comments from Ken", "Drop Down Item 4"},
        };
    }

    @DataProvider
    public Object[][] getFakeRecords() {
        Faker faker = new Faker();
        return new Object[][]{
                {faker.name().username(), faker.internet().password(), faker.lorem().paragraph(50), "Drop Down Item "+faker.number().numberBetween(1,7)},
                {faker.name().username(), faker.internet().password(), faker.lorem().paragraph(50), "Drop Down Item "+faker.number().numberBetween(1,7)},
                {faker.name().username(), faker.internet().password(), faker.lorem().paragraph(50), "Drop Down Item "+faker.number().numberBetween(1,7)},
                {faker.name().username(), faker.internet().password(), faker.lorem().paragraph(50), "Drop Down Item "+faker.number().numberBetween(1,7)},
                {faker.name().username(), faker.internet().password(), faker.lorem().paragraph(50), "Drop Down Item "+faker.number().numberBetween(1,7)},
                {faker.name().username(), faker.internet().password(), faker.lorem().paragraph(50), "Drop Down Item "+faker.number().numberBetween(1,7)}
        };
    }


}
