module com.example.bird_sniping {
    
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.bird_sniping to javafx.fxml;
    exports com.example.bird_sniping;
}