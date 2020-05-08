package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import pojos.Department;
import pojos.MedicalProfessional;
import pojos.Pacient;
import pojos.Staff;

public class departmentViewController implements Initializable {

	@FXML
	private TableView departmentsTable;

	@FXML
	private TableColumn<Department, Integer> idColumn;
	@FXML
	private TableColumn<Department, String> nameColumn;
	@FXML
	private TableColumn<Department, Integer> floorColumn;
	@FXML
	private TableColumn<Department, Integer> budgetColumn;

	@FXML
	private TableView medicalProfessionalTable;

	@FXML
	private TableColumn<MedicalProfessional, Integer> mpIdColumn;
	@FXML
	private TableColumn<MedicalProfessional, String> mpNameColumn;
	@FXML
	private TableColumn<MedicalProfessional, String> mpProfessionColumn;

	@FXML
	private TableView staffTable;

	@FXML
	private TableColumn<Staff, Integer> stIdColumn;
	@FXML
	private TableColumn<Staff, String> stNameColumn;
	@FXML
	private TableColumn<Staff, String> stProfessionColumn;

	@FXML
	private TextField txtSearch;

	@FXML
	private Button deleteDep, editDep;

	private ObservableList<Department> departmentList = FXCollections.observableArrayList();
	FilteredList departmentFilter = new FilteredList(departmentList, e -> true);

	private ObservableList<MedicalProfessional> medicalProfessionalList = FXCollections.observableArrayList();
	FilteredList mpFilter = new FilteredList(medicalProfessionalList, e -> true);

	private ObservableList<Staff> staffList = FXCollections.observableArrayList();
	FilteredList staffFilter = new FilteredList(staffList, e -> true);

	private DBConnection dbConnection = new DBConnection();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// disable the edit button until the department has been selected
		this.editDep.setDisable(true);
		this.deleteDep.setDisable(true);

		idColumn.setCellValueFactory(new PropertyValueFactory<Department, Integer>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Department, String>("name"));
		floorColumn.setCellValueFactory(new PropertyValueFactory<Department, Integer>("floor"));
		budgetColumn.setCellValueFactory(new PropertyValueFactory<Department, Integer>("budget"));

		mpIdColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, Integer>("id"));
		mpNameColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, String>("name"));
		mpProfessionColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, String>("profession"));

		stIdColumn.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("id"));
		stNameColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("name"));
		stProfessionColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("profession"));

		// inicializamos la tabla

		departmentsTable.setItems(loadDepartments());

		// Staff staf = new Staff("maria", LocalDate.now(), "male",
		// "receptionits", "email", "calle", 5667778, "555555T", 1, "human
		// resources", 1);
		// staffList.add(staf);
		// staffTable.setItems(staffList);
	}

	public ObservableList<Department> loadDepartments() {

		List<Department> departments = null;

		departments = this.dbConnection.listAllDepartments();

		departmentList.addAll(departments);

		return departmentList;
	}

	public void addButton(ActionEvent event) {

		SceneChanger sc = new SceneChanger();
		sc.changeScenes(event, "newDepartment.fxml", "New Department");

	}

	public void loadData(Department department) {

		// lo primero limpiamos las tables

		this.staffTable.getItems().clear();
		this.medicalProfessionalTable.getItems().clear();

		// --------------------------------------------------------------------
//System.out.println(department);


		this.medicalProfessionalList.clear();
		this.staffList.clear();

		medicalProfessionalList.addAll(department.getMedicalProfessionalList());
		staffList.addAll(department.getStaffList());



		this.medicalProfessionalTable.setItems(medicalProfessionalList);
		this.staffTable.setItems(staffList);
	}

	public void departmentSelected() {

		this.editDep.setDisable(false);
		this.deleteDep.setDisable(false);

		Department dep = (Department) this.departmentsTable.getSelectionModel().getSelectedItem();

		Integer id = dep.getId();

		System.out.println(id);

		Department department = this.dbConnection.searchDepartmentById(id);

		loadData(department);
	}

	public void addStaff(ActionEvent event) {

		Staff staff = new Staff("juan", LocalDate.now(), "male", "receptionits", "email", "calle", 5667778, "555555T",
				2);
		this.dbConnection.addStaff(staff);
	}

	public void addMedicalProfessional(ActionEvent event) {

			//SceneChanger sc = new SceneChanger();
			//sc.changeScenes(event, "newMedicalProfessional.fxml", "New Medical Professional");

		MedicalProfessional mp = new MedicalProfessional(1, "marta", LocalDate.now(), "female", "surgeon", "email",
				"calle", 5667778, "555555T", 3);
		this.dbConnection.addMedicalProfessional(mp);

	}

	public void deleteDepartment(ActionEvent event) {

		Department dep = (Department) this.departmentsTable.getSelectionModel().getSelectedItem();

		this.dbConnection.deleteDepartment(dep.getId());

		this.departmentsTable.getItems().removeAll(departmentsTable.getSelectionModel().getSelectedItem());
		this.medicalProfessionalTable.getItems().clear();
		this.staffTable.getItems().clear();
	}

	public void editDepartment(ActionEvent event) {

		SceneChanger sc = new SceneChanger();
		Department dep = (Department) this.departmentsTable.getSelectionModel().getSelectedItem();
		NewDepartmentController ndc = new NewDepartmentController();
		sc.loadDepartmentWithData(event, "newDepartment.fxml", "Edit Department", dep, ndc);
	}

}
