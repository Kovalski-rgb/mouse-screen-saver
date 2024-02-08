package utils;

import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class ScreenSaver implements Runnable   {
    private final ScheduledExecutorService scheduledExecutorService;
    private ScheduledFuture<?> scheduledFuture;
    private Runnable screenSaver;
    private static int TIME_PERIOD = 1000;
    private boolean isTaskRunning;

    public ScreenSaver() {
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
        this.screenSaver = getOldScreenSaver();
        this.isTaskRunning = false;
    }

    public void setScreenSaver(Runnable screenSaver) {
        if(scheduledFuture != null){
            this.stop();
        }
        this.screenSaver = screenSaver;
    }

    public void toggle(){
        if(isTaskRunning){
            stop();
        }else{
            start();
        }
    }

    public void start(){
        isTaskRunning = true;
        scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(this, 0, TIME_PERIOD, TimeUnit.MILLISECONDS);
    }

    public void stop(){
        isTaskRunning = false;
        scheduledFuture.cancel(true);
    }

    public void run() {
        this.screenSaver.run();
    }


    private static Point check_bounds(Point point){
        Rectangle bounds = getBounds();
        Point newOrientation = new Point(0, 0);
        if(point.x-1 <= bounds.x){
            newOrientation.x=+1;
        } else if (point.x+1 >= bounds.width) {
            newOrientation.x=-1;
        }
        if(point.y-1 <= bounds.y){
            newOrientation.y=+1;
        } else if (point.y+1 >= bounds.height) {
            newOrientation.y=-1;
        }
        return newOrientation;
    }

    private static void updateOrientation(Point oldOrientation, Point newOrientation){
        if(newOrientation.x != 0){
            oldOrientation.x = newOrientation.x;
        }
        if(newOrientation.y != 0){
            oldOrientation.y = newOrientation.y;
        }
    }
    private static Rectangle getBounds() {
        GraphicsDevice device = MouseInfo.getPointerInfo().getDevice();
        GraphicsConfiguration gc = device.getDefaultConfiguration();
        Rectangle bounds = gc.getBounds();
        bounds.width = bounds.x+bounds.width-1;
        bounds.height = bounds.y+bounds.height-1;
        return bounds;
    }


    public static Runnable getMinimalScreenSaver() {
        TIME_PERIOD = 1000;

        return new Runnable() {
            int orientation = +1;

            public void run() {
                try {
                    Robot robot = new Robot();
                    Point coord = MouseInfo.getPointerInfo().getLocation();
                    robot.mouseMove(coord.x + orientation, coord.y + orientation);
                    orientation *= -1;
                } catch (AWTException ignored) {
                }
            }
        };
    }

    public static Runnable getOldScreenSaver() {
        TIME_PERIOD = 1;
        return new Runnable() {
            final Point orientation = new Point(+1, +1);
            public void run() {
                try {
                    Point point = MouseInfo.getPointerInfo().getLocation();
                    Robot robot = new Robot();

                    robot.mouseMove(point.x + orientation.x, point.y + orientation.y);
                    Point newOrientation = check_bounds(point);
                    updateOrientation(orientation, newOrientation);
                } catch (AWTException ignored) {
                }
            }
        };
    }
}
