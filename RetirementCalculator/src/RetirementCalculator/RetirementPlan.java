package RetirementCalculator;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class RetirementPlan {
    public static SimpleStringProperty planName;
    public static SimpleStringProperty clientName;
    public static SimpleStringProperty color;
    public static SimpleListProperty plan;
    public static List listyboi;
    public static String plnNameStr;
    public static String clntNameStr;
    public static String scnColorStr;
    public static SimpleStringProperty enoughMoney;
    public static List varList;
    public static Double peakSavingsRaw;
    public static Double remainBalRaw;
    public static SimpleDoubleProperty peakSavings;
    public static SimpleDoubleProperty remainBal;

    public RetirementPlan(String planname, String clientname, List planList){
        varList = (List) planList.get(0);
        planName = new SimpleStringProperty(planname);
        clientName = new SimpleStringProperty(clientname);
        var planlistobs = FXCollections.observableArrayList();
        planlistobs.addAll(planList);
        plan = new SimpleListProperty(planlistobs);
        listyboi = planList;
        plnNameStr = planname;
        clntNameStr = clientname;
        enoughMoney = new SimpleStringProperty((String) varList.get(20));
        peakSavingsRaw = Main.round((Double) varList.get(21), 2);
        peakSavings = new SimpleDoubleProperty(peakSavingsRaw);
        remainBalRaw  = Main.round((Double) varList.get(22), 2);
        remainBal = new SimpleDoubleProperty(remainBalRaw);

    }

    public static String getPlanName(){
        return planName.get();
    }
    public static String getClientName(){
        return clientName.get();
    }
    public static String getColor(){
        return color.get();
    }
    public ObservableList getPlan(){
        return plan.get();
    }
    public List getPlainList(){
        return listyboi;
    }
    public String getTablePlanName() {return planName.get();}
    public String getTableClientName() {return clientName.get();}
    public String getTableScenarioColor() {return color.get();}
    public String getEnoughMoney(){return enoughMoney.get();}
    public Integer getPlanLength(){return listyboi.size()-1;}
    public Double getTablePeakSavings(){return peakSavings.get();}
    public Double getRemainBal(){return remainBal.get();}
    public List getVarList(){return varList;}
}

