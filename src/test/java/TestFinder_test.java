import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestFinderTest {

    private final TestFinder testFinder = new TestFinder();

    @ParameterizedTest()
    @CsvSource({
            "ababcTESTabc, F",
            "abbcTES, S3",

            "TEST, F",
            "T, S1",
            "TE, S2",
            "TES, S3",

            "abc, S",
            ", S",
            "EST, S1",
            "ES, S",

            "adasdTTESTabb, F",
            "TETEST, F",
            "TESTEST, F",
            "TTTTEST, F"
    })
    void testFsmStates(String input, TestFinder.States expectedState) {
        String actualInput = input == null ? "" : input;
        assertEquals(expectedState, testFinder.Process(actualInput));
    }
}