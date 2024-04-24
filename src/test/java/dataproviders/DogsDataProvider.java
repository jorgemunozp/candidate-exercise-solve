package dataproviders;

import org.testng.annotations.DataProvider;

public class DogsDataProvider {

    @DataProvider(name = "TestData")
    public static Object[][] testData(){
        return new Object[][]{
                {"breed", "German Shepherd", "age", 5, "name", "Gaira"},
                {"breed", "Chihuahua", "age", 4, "name", "Thor"},
                {"breed", "Labrador", "age", 9, "name", "Morrison"},
                {"breed", "Native", "age", 2, "name", "Murdock"},
                {"breed", "Border Collie", "age", 1, "name", "Simona"},
        };
    }

}
