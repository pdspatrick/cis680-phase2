package RetirementCalculator;

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

    public RetirementPlan(String planname, String clientname, String colour, List planList){
        varList = (List) planList.get(0);
        planName = new SimpleStringProperty(planname);
        clientName = new SimpleStringProperty(clientname);
        color = new SimpleStringProperty(colour);
        var planlistobs = FXCollections.observableArrayList();
        planlistobs.addAll(planList);
        plan = new SimpleListProperty(planlistobs);
        listyboi = planList;
        plnNameStr = planname;
        clntNameStr = clientname;
        scnColorStr = colour;
        enoughMoney = new SimpleStringProperty((String) varList.get(20));


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
}

