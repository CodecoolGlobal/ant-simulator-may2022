module com.codecool.ants.ants_sim {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.codecool.ants.ants_sim to javafx.fxml;
    exports com.codecool.ants.ants_sim;
}