import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "users")
    public static Object[][] dataProviderMethod(){
        return new Object[][]{
                {"{\"name\":\"Maxim\",\"job\":\"QA\"}"},
                {"{\"name\":\"Andrey\",\"job\":\"dev\"}"},
                {"{\"name\":\"Jane\",\"job\":\"BA\"}"}
        };
    }
    @DataProvider(name ="usersPost")
    public static Object[][] dataProviderMethodForPosts(){
        return new Object[][]{
                {"{\"userId\": 666,\"id\": 101, \"title\": \"evil\", \"body\": \"Don't touch my wife\" }"},
                {"{\"userId\": 777,\"id\": 102, \"title\": \"good\", \"body\": \"low and justice\" }"},
                {"{\"userId\": 888,\"id\": 103, \"title\": \"neutral\", \"body\": \"I don't care\" }"}
        };
    }
}

