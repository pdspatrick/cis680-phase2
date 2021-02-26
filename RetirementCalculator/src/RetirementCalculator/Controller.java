package RetirementCalculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;


import java.util.List;

import static RetirementCalculator.Main.dataInput;


public class Controller {

    @FXML
    public GridPane inputArea;
    @FXML
    private TextField yearlyContrib;
    @FXML
    private TextField currentIncome;
    @FXML
    private TextField currentSavings;
    @FXML
    private TextField yearlyNontaxableContrib;
    @FXML
    private TextField rateOfRetirementReturn;
    @FXML
    private TextField rateOfReturn;
    @FXML
    private TextField rateOfInflation;
    @FXML
    public TextField age;
    @FXML
    private TextField age80Income;
    @FXML
    private TextField desiredIncome;
    @FXML
    private TextField retirementAge;
    @FXML
    private TextField clientName;
    @FXML
    private TextField scenarioName;
    @FXML
    private ChoiceBox<?> scenarioColor;
    @FXML
    private TextField incomeRise;
    @FXML
    private TableView<RetirementPlan> dataTable;
    @FXML
    public TableColumn<RetirementPlan, String> tablePeakSavings;
    @FXML
    public TableColumn<RetirementPlan, String> tablePlanName;
    @FXML
    public TableColumn<RetirementPlan, String> enoughMoney;
    @FXML
    private LineChart plansChart;
    @FXML
    protected TableColumn<RetirementPlan, String> tableClientName;
    @FXML
    protected Label remainBalText;
    @FXML
    protected Label peakSavingText;
    @FXML
    protected Label planNameText;
    @FXML
    protected Label clientNameText;
    @FXML
    protected LineChart yearlyIncomeChart;
    public void dataSubmit(ActionEvent actionEvent) {
        var dataset = dataInput(clientName.getText(),
                scenarioName.getText(),
                checkForNull(yearlyContrib.getText()),
                checkForNull(currentSavings.getText()),
                checkForNull(currentIncome.getText()),
                checkForNull(incomeRise.getText()),
                checkForNull(rateOfReturn.getText()),
                checkForNull(rateOfRetirementReturn.getText()),
                checkForNull(age.getText()),
                checkForNull(retirementAge.getText()),
                checkForNull(desiredIncome.getText()),
                checkForNull(age80Income.getText()),
                checkForNull(rateOfInflation.getText()));
        var plan = new RetirementPlan(scenarioName.getText(), clientName.getText(), dataset);
        XYChart.Series dataplot = new XYChart.Series();
        dataplot.setName(plan.getTablePlanName());
        XYChart.Series dataplot2 = new XYChart.Series();
        dataplot2.setName(plan.getTablePlanName());
        for (int i = 1; i < plan.getPlanLength() + 1; i++){
            var current = (List) dataset.get(i);
            dataplot.getData().add(new XYChart.Data(current.get(0), current.get(1)));
            dataplot2.getData().add(new XYChart.Data(current.get(0), current.get(2)));
        }
        plansChart.getData().add(dataplot);
        yearlyIncomeChart.getData().add(dataplot2);


    }

    public void dataUpdate(MouseEvent actionEvent){
        var selected = dataTable.getSelectionModel().getSelectedIndex();
        var inputVars = Main.savedData.get(selected).getVarList();
        remainBalText.setText(inputVars.get(22).toString());
        peakSavingText.setText(inputVars.get(21).toString());
        planNameText.setText(inputVars.get(1).toString());
        clientNameText.setText(inputVars.get(0).toString());
    }
    public static String checkForNull(String input){
        if (input.isBlank()) return "0";
        if (input.isEmpty()) return "0";
        return input.toString();
    }



}

