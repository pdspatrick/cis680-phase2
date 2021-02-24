package RetirementCalculator;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class RetirementPlan {
    public static SimpleStringProperty plnName;
    public static SimpleStringProperty clntName;
    public static SimpleStringProperty scnColor;
    public static SimpleListProperty fulllist;
    public static List listyboi;
    public static String plnNameStr;
    public static String clntNameStr;
    public static String scnColorStr;

    RetirementPlan(String planname, String clientname, String color, List planList){
        plnName = new SimpleStringProperty(planname);
        clntName = new SimpleStringProperty(clientname);
        scnColor = new SimpleStringProperty(color);
        fulllist = new SimpleListProperty((ObservableList) planList);
        listyboi = planList;
        plnNameStr = planname;
        clntNameStr = clientname;
        scnColorStr = color;

    }

    public String getPlanName(){
        return plnName.get();
    }
    public String getCLientName(){
        return clntName.get();
    }
    public String getColor(){
        return scnColor.get();
    }
    public List getPlan(){
        return listyboi;
    }
    public ObservableList getObsList(){
        return fulllist;
    }
}

