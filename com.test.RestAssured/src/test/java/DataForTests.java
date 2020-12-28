import org.testng.annotations.DataProvider;

public class DataForTests {

    @DataProvider(name = "DataForPost")
    public Object[][] dataForPost(){
/*        Object[][] data = new Object[2][3];

        data[0][0] = "Albert";
        data[0][1] = "Einstain";
        data[0][2] = 2;

        data[1][0] = "Thomas";
        data[1][1] = "Edison";
        data[1][2] = 1;

        return data;*/

        return new Object[][] {
                {"Graham", "Bell", 1},
                {"Henry", "Ford", 2},
                {"Stranger1", "Doe", 3},
                {"Stranger2", "Doe", 1},
                {"Stranger3", "Doe", 3},

        };

    }

    @DataProvider(name = "DeleteData")
    public Object[] dataForDelete(){

        return new Object[]{
                3,4,5,6,7
        };
    }


}



