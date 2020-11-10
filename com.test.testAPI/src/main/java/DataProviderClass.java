import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "users")
    public static Object[][] dataProviderMethod(){
        return new Object[][]
                {{"{\"name\":\"Maxim\",\"job\":\"QA\"}"},
                 {"{\"name\":\"Andrey\",\"job\":\"dev\"}"},
                 {"{\"name\":\"Jane\",\"job\":\"BA\"}"}
                };
        }
    }

