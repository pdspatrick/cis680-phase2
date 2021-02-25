package RetirementCalculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
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
    private Label peakSavingText;
    @FXML
    private TextField scenarioName;
    @FXML
    private ChoiceBox<?> scenarioColor;
    @FXML
    private TextField incomeRise;
    @FXML
    private TableView<RetirementPlan> dataTable;
    @FXML
    public TableColumn<RetirementPlan, String> tableScenarioColor;
    @FXML
    public TableColumn<RetirementPlan, String> tablePlanName;
    @FXML
    public TableColumn<RetirementPlan, String> enoughMoney;
    @FXML
    private LineChart plansChart;
    @FXML
    protected TableColumn<RetirementPlan, String> tableClientName;
    public void dataSubmit(ActionEvent actionEvent) {
        var dataset = dataInput(clientName.getText(),
                scenarioName.getText(),
                scenarioColor.getSelectionModel().getSelectedItem().toString(),
                yearlyContrib.getText(),
                currentSavings.getText(),
                currentIncome.getText(),
                incomeRise.getText(),
                rateOfReturn.getText(),
                rateOfRetirementReturn.getText(),
                age.getText(),
                retirementAge.getText(),
                desiredIncome.getText(),
                age80Income.getText());
        var plan = new RetirementPlan(scenarioName.getText(), clientName.getText(), scenarioColor.getSelectionModel().getSelectedItem().toString(), dataset);
        tablePlanName.setCellValueFactory(new PropertyValueFactory<RetirementPlan, String>("tablePlanName"));
        tableClientName.setCellValueFactory(new PropertyValueFactory<RetirementPlan, String>("tableClientName"));
        tableScenarioColor.setCellValueFactory(new PropertyValueFactory<RetirementPlan, String>("tableScenarioColor"));
        enoughMoney.setCellValueFactory(new PropertyValueFactory<RetirementPlan, String>("enoughMoney"));
        Main.savedData.add(Main.savedData.size(), plan);
        dataTable.setItems(Main.savedData);
        XYChart.Series dataplot = new XYChart.Series();
        dataplot.setName(plan.getTablePlanName());
        for (int i = 1; i < plan.getPlanLength() + 1; i++){
            var current = (List) dataset.get(i);
            dataplot.getData().add(new XYChart.Data(current.get(0), current.get(1)));
        }
        plansChart.getData().add(dataplot);

    }

    public void dataUpdate(ActionEvent actionEvent){
        return;
    }

}

