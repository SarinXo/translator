package sarinxo.desctop.translator;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.scene.Cursor.E_RESIZE;
import static javafx.scene.Cursor.NE_RESIZE;
import static javafx.scene.Cursor.NW_RESIZE;
import static javafx.scene.Cursor.N_RESIZE;
import static javafx.scene.Cursor.SE_RESIZE;
import static javafx.scene.Cursor.SW_RESIZE;
import static javafx.scene.Cursor.S_RESIZE;
import static javafx.scene.Cursor.W_RESIZE;

public class ResizeHelper {

    private ResizeHelper() {}

    public static void addResizeListener(Stage stage, int margin) {
        Scene scene = stage.getScene();
        final Delta delta = new Delta();

        scene.setOnMouseMoved(e -> {
            double x = e.getSceneX(), y = e.getSceneY();
            double w = scene.getWidth(), h = scene.getHeight();

            boolean left   = x <= margin;
            boolean right  = x >= w - margin;
            boolean top    = y <= margin;
            boolean bottom = y >= h - margin;

            if (left && top)            scene.setCursor(NW_RESIZE);
            else if (right && top)      scene.setCursor(NE_RESIZE);
            else if (left && bottom)    scene.setCursor(SW_RESIZE);
            else if (right && bottom)   scene.setCursor(SE_RESIZE);
            else if (right)             scene.setCursor(E_RESIZE);
            else if (left)              scene.setCursor(W_RESIZE);
            else if (top)               scene.setCursor(N_RESIZE);
            else if (bottom)            scene.setCursor(S_RESIZE);
            else                        scene.setCursor(Cursor.DEFAULT);
        });

        scene.setOnMousePressed(e -> {
            delta.x = e.getScreenX();
            delta.y = e.getScreenY();
            delta.stageX = stage.getX();
            delta.stageY = stage.getY();
            delta.stageW = stage.getWidth();
            delta.stageH = stage.getHeight();
            delta.cursor = scene.getCursor();
        });

        scene.setOnMouseDragged(e -> {
            if (delta.cursor == Cursor.DEFAULT) return;

            double dx = e.getScreenX() - delta.x;
            double dy = e.getScreenY() - delta.y;

            if (delta.cursor == Cursor.E_RESIZE ||
                    delta.cursor == Cursor.NE_RESIZE ||
                    delta.cursor == Cursor.SE_RESIZE) {
                stage.setWidth(clamp(delta.stageW + dx, stage.getMinWidth(), stage.getMaxWidth()));
            } else if (delta.cursor == Cursor.W_RESIZE ||
                    delta.cursor == Cursor.NW_RESIZE ||
                    delta.cursor == Cursor.SW_RESIZE) {
                double newW = clamp(delta.stageW - dx, stage.getMinWidth(), stage.getMaxWidth());
                double xShift = delta.stageW - newW;
                stage.setX(delta.stageX + xShift);
                stage.setWidth(newW);
            }

            if (delta.cursor == Cursor.S_RESIZE ||
                    delta.cursor == Cursor.SE_RESIZE ||
                    delta.cursor == Cursor.SW_RESIZE) {
                stage.setHeight(clamp(delta.stageH + dy, stage.getMinHeight(), stage.getMaxHeight()));
            } else if (delta.cursor == Cursor.N_RESIZE ||
                    delta.cursor == Cursor.NE_RESIZE ||
                    delta.cursor == Cursor.NW_RESIZE) {
                double newH = clamp(delta.stageH - dy, stage.getMinHeight(), stage.getMaxHeight());
                double yShift = delta.stageH - newH;
                stage.setY(delta.stageY + yShift);
                stage.setHeight(newH);
            }
        });


        scene.setOnMouseReleased(e -> scene.setCursor(Cursor.DEFAULT));
    }

    private static double clamp(double v, double min, double max) {
        if (max > 0 && v > max) return max;
        return Math.max(v, min);
    }

    private static final class Delta {
        double x, y;
        double stageX, stageY, stageW, stageH;
        Cursor cursor = Cursor.DEFAULT;
    }
}
