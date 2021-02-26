package RetirementCalculator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import RetirementCalculator.Controller;
import java.util.logging.*;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("startui.fxml"));
        primaryStage.setTitle("🍩 Donut Retirement Calculator 🍩");
        primaryStage.setScene(new Scene(root, 900, 800));
        primaryStage.show();
    }
    public static List<RetirementPlan> savedDataList = new ArrayList<RetirementPlan>();
    public static ObservableList<RetirementPlan> savedData = FXCollections.observableList(savedDataList);

    public static void main(String[] args) {
        launch(args);
    }

    public static List dataInput(String clientName,
                                 String scenarioName,
                                 String yearlyContrib,
                                 String currentSavings,
                                 String currentIncome,
                                 String incomeRise,
                                 String rateOfReturn,
                                 String rateOfRetirementReturn,
                                 String age,
                                 String retirementAge,
                                 String desiredIncome,
                                 String age80Income,
                                 String inflationRate){
        List output = new ArrayList();
        List inputVars = new ArrayList(); //storing the variables used to create the calculations here, which will become output[0]
        Integer yearsUntilRetirement = Integer.parseInt(retirementAge) - Integer.parseInt(age);
        inputVars.add(0,clientName);
        inputVars.add(1,scenarioName);
        inputVars.add(2,inflationRate);
        inputVars.add(3,Double.parseDouble(yearlyContrib));
        inputVars.add(4,Double.parseDouble(currentSavings));
        inputVars.add(5,Double.parseDouble(currentIncome));
        inputVars.add(6,Double.parseDouble(incomeRise));
        inputVars.add(7,Double.parseDouble(rateOfReturn));
        inputVars.add(8,Double.parseDouble(rateOfRetirementReturn));
        inputVars.add(9,Integer.parseInt(age));
        inputVars.add(10,Integer.parseInt(retirementAge));
        inputVars.add(11,Double.parseDouble(desiredIncome));
        inputVars.add(12,Double.parseDouble(age80Income));
        inputVars.add(13,yearsUntilRetirement);
        var peakSavings = (Double) 0.0;

        output.add(0, inputVars);
        var incomeRisePlus1 = checkPercent(Double.parseDouble(incomeRise));
        var savingsRisePlus1 = checkPercent(Double.parseDouble(rateOfReturn));
        var retirementRatePlus1 = checkPercent(Double.parseDouble(rateOfRetirementReturn));
        var salary = Double.parseDouble(currentIncome);
        var totalSavings = Double.parseDouble("0");
        var contribution = checkPercent(Double.parseDouble(yearlyContrib)) - 1; //done to counter the +1 done in the function
        var activeRetirementYears = 80 - Integer.parseInt(retirementAge);
        var currentAge = Integer.parseInt(age);
        var activeIncome = Double.parseDouble(desiredIncome);
        var plus80income = Double.parseDouble(age80Income);
        var inflationRatePlus1 = checkPercent(Double.parseDouble(inflationRate));
        for (int i = 0; i < yearsUntilRetirement; i++){
            if (i > 0){
                salary = salary * incomeRisePlus1;
                totalSavings = totalSavings * savingsRisePlus1;
                currentAge++;


            }
            totalSavings = totalSavings + (salary * (contribution));
            if (totalSavings > peakSavings) peakSavings = totalSavings;
            var liss = List.of(currentAge, totalSavings, salary);
            //System.out.println(currentAge + " in year " + i + " of preretirement with " + totalSavings);
            output.add(liss);
            if(i == yearsUntilRetirement - 1){
                inputVars.add(14, totalSavings);
                inputVars.add(15, currentAge);
            }}
        for (int i = 0; i < activeRetirementYears; i++){
            var activeIncome2 = activeIncome;
            totalSavings = totalSavings * retirementRatePlus1;
            totalSavings = totalSavings - activeIncome;
            activeIncome = activeIncome * inflationRatePlus1;

            currentAge++;
            if (totalSavings > peakSavings) peakSavings = totalSavings;

            var liss = List.of(currentAge, totalSavings, activeIncome2);
            //System.out.println(currentAge + " in year " + i + " of retirement with " + totalSavings);
            output.add(liss);
            if (i == activeRetirementYears - 1){
                inputVars.add(16, totalSavings);
                inputVars.add(17, currentAge);
            }
            }
        for (int i = 0; i < 21; i++){
            var plus80income2 = plus80income;
            totalSavings = totalSavings * retirementRatePlus1;
            totalSavings = totalSavings - plus80income;
            activeIncome = activeIncome * inflationRatePlus1;
            plus80income = plus80income * inflationRatePlus1;

            currentAge++;
            if (totalSavings > peakSavings) peakSavings = totalSavings;

            var liss = List.of(currentAge, totalSavings, plus80income2);
            //System.out.println(currentAge + " in year " + i + " of late retirement with " + totalSavings);
            output.add(liss);
            if (i == 20){
                inputVars.add(18, totalSavings);
                inputVars.add(19, currentAge);
            }
        }
        if (totalSavings > 0) inputVars.add(20, "Yes");
        else inputVars.add(20,"No");
        inputVars.add(21, peakSavings);
        inputVars.add(22, totalSavings);
        output.set(0, inputVars);

        return output;
    }
    public static double checkPercent(Double numberToCheck){
        var output = Double.parseDouble("0.0");
        if (numberToCheck > 1) output = (numberToCheck / 100) + 1;
        else output = numberToCheck + 1;
        return output;
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}
