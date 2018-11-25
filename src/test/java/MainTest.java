import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void main() {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(MainTest.class);
    }

    @Test
    public void addPersons() {
    }


}