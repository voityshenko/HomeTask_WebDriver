package service;

import model.Form;

public class FormCreator {
    public static final String TESTDATA_SERIES_MACHINE = "testdata.user.seriesMachine";
    public static final String TESTDATA_GPU_TYPE = "testdata.form.gpuType";
    public static final String TESTDATA_NUMBERS_GPU = "testdata.user.numbersGpu";

    public static Form withCredentialsFromProperty() {
        return new Form(TestDataReader.getTestData(TESTDATA_SERIES_MACHINE),
                TestDataReader.getTestData(TESTDATA_GPU_TYPE),
                TestDataReader.getTestData(TESTDATA_NUMBERS_GPU));
    }
//    public static Form withEmptyUserName () {
//        return new Form ("", TestDataReader.getTestData(TESTDATA_USER_PASSWORD));
//    }
//    public static Form withEmptyPassword () {
//        return new Form (TestDataReader.getTestData(TESTDATA_USER_NAME), "");
//    }

}
