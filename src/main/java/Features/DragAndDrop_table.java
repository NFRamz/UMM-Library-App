package Features;

import books.Book;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class DragAndDrop_table extends TableCell<Book, String> {
    private final TextField bookIdField;

    public DragAndDrop_table(TextField bookIdField) {
        this.bookIdField = bookIdField;
        this.setOnMouseClicked(event -> mouseClick(event));
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            setText(item);
        } else {
            setText(null);
        }
    }


    private void mouseClick(MouseEvent event) {
        Book selectBook   = getTableView().getSelectionModel().getSelectedItem();

        if (event.getClickCount() == 2) {

                String bookId = selectBook.getBookId();
                bookIdField.setText(bookId);

        }
    }
}