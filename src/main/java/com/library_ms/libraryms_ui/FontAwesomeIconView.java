package com.library_ms.libraryms_ui;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.kordamp.ikonli.javafx.FontIcon;

public class FontAwesomeIconView extends FontIcon {
    public FontAwesomeIconView() {
        super();
    }

    public FontAwesomeIconView(String iconCode) {
        super(iconCode);
    }

    public void setIconCode(String iconCode) {
        setIconLiteral(iconCode);
    }

    public void setSize(int size) {
        setIconSize(size);
    }

    public void setColor(String color) {
        setIconColor(Color.web(color));
    }
}
