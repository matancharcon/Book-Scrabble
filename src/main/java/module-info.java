module com.example.bookscrabble {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bookscrabble to javafx.fxml;
    //exports com.example.bookscrabble;
    exports view;
    opens view to javafx.fxml;
}