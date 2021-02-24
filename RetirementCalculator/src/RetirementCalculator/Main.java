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
import java.util.ArrayList;
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
    public static ObservableList<RetirementPlan> savedData = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    public static List dataInput(String clientName,
                                 String scenarioName,
                                 String scenarioColor,
                                 String yearlyContrib,
                                 String currentSavings,
                                 String currentIncome,
                                 String incomeRise,
                                 String rateOfReturn,
                                 String rateOfRetirementReturn,
                                 String age,
                                 String retirementAge,
                                 String desiredIncome,
                                 String age80Income){
        List output = new ArrayList();
        List inputVars = new ArrayList(); //storing the variables used to create the calculations here, which will become output[0]
        Integer yearsUntilRetirement = Integer.parseInt(retirementAge) - Integer.parseInt(age);
        inputVars.add(0,clientName);
        inputVars.add(1,scenarioName);
        inputVars.add(2,scenarioColor);
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

        output.add(0, inputVars);
        var incomeRisePlus1 = Double.parseDouble(incomeRise) + 1;
        var savingsRisePlus1 = Double.parseDouble(rateOfReturn) + 1;
        var retirementRatePlus1 = Double.parseDouble(rateOfRetirementReturn) + 1;
        var salary = Double.parseDouble(currentIncome);
        var totalSavings = Double.parseDouble("0");
        var contributionPlus1 = Double.parseDouble(yearlyContrib) + 1;
        var activeRetirementYears = 80 - Integer.parseInt(retirementAge);
        var currentAge = Integer.parseInt(age);
        var activeIncome = Double.parseDouble(desiredIncome);
        var plus80income = Double.parseDouble(age80Income);
        for (int i = 0; i < yearsUntilRetirement; i++){
            if (i > 0){
                salary = salary * incomeRisePlus1;
                totalSavings = totalSavings * savingsRisePlus1;
                currentAge++;
                print(currentAge);
            }
            totalSavings = totalSavings + (salary * contributionPlus1);
            var liss = List.of(currentAge, totalSavings);
            output.add(liss);
            if(i == yearsUntilRetirement - 1){
                inputVars.add(14, totalSavings);
                inputVars.add(15, currentAge);
            }}
        for (int i = 0; i < activeRetirementYears; i++){
            totalSavings = totalSavings * retirementRatePlus1;
            totalSavings = totalSavings - activeIncome;
            currentAge++;
            print(currentAge);
            var liss = List.of(currentAge, totalSavings);
            output.add(liss);
            if (i == activeRetirementYears - 1){
                inputVars.add(16, totalSavings);
                inputVars.add(17, currentAge);
            }
            }
        for (int i = 0; i < 21;){
            totalSavings = totalSavings * retirementRatePlus1;
            totalSavings = totalSavings - plus80income;
            currentAge++;
            print(currentAge);
            var liss = List.of(currentAge, totalSavings);
            output.add(liss);
            if (i == 20){
                inputVars.add(18, totalSavings);
                inputVars.add(19, currentAge);
            }
        }
        output.set(0, inputVars);

        return output;
    }

}
