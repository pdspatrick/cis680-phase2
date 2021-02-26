import RetirementCalculator.Main;
import RetirementCalculator.RetirementPlan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class primaryTests{
    @Test
    void willItRunAtAll(){
        var testtool = Main.dataInput("patrick", "yee",  ".10", "0", "60000", ".02", ".045", ".04", "30", "70", "44000", "44000", "1.5");
        List testvars;
        testvars = (List) testtool.get(0);
        Assertions.assertEquals(testvars.get(19), 100);
    }

    @Test
    void retirementPlanObjectTest(){
        var testtool = Main.dataInput("patrick",
                "yee",
                ".10",
                "0",
                "60000",
                ".02",
                ".045",
                ".04",
                "30",
                "70",
                "44000",
                "44000",
                "1.5");
        var testvars = (List) testtool.get(0);
        var plan = new RetirementPlan(testvars.get(1).toString(), testvars.get(0).toString(), testtool);
        Assertions.assertEquals(plan.getPlanName(), testvars.get(1));
        Assertions.assertEquals(plan.getClientName(), testvars.get(0));
        Assertions.assertEquals(plan.getColor(), testvars.get(2));
        Assertions.assertEquals(plan.getPlan(), testtool);
    }
}