package org.firstinspires.ftc.teamcode.PrimeTech.Components;

import android.view.Display;
import android.widget.Switch;

import org.firstinspires.ftc.teamcode.PrimeTech.Gamepad.Gamepad;

public class Modes {
    private Modes instance = null;

    public synchronized Modes getInstance(){
        if(instance == null){
            instance = new Modes();
        }
        return instance;
    }
    public void init(){

    }


    public  Components components = Components.EXTENSION;
    public void Reset(){
        switch (components){
            case IDLE:
                if(Gamepad.getInstance().dpad_down()) {
                    components = Components.EXTENSION;
                }
            case EXTENSION:
                Extension.getInstance().setTarget(0);
                if(Extension.getInstance().isDone()){
                    components=Components.PIVOT;
                }
                break;
            case PIVOT:
                Pivot.getInstance().setTarget(0);
                if(Pivot.getInstance().isDone()){
                    components=Components.IDLE;
                }
                break;
        }
    }
    public void Intake(){
        switch (components){
            case IDLE:
                if(Gamepad.getInstance().dpad_up()) {
                    components = Components.EXTENSION;
                }
            case EXTENSION:
                Extension.getInstance().setTarget(1000);
                if(Extension.getInstance().isDone()){
                    components=Components.PIVOT;
                }
                break;
            case PIVOT:
                Pivot.getInstance().setTarget(0);
                if(Pivot.getInstance().isDone()){
                    components=Components.IDLE;
                }
                break;
        }
    }
    public enum Components{
        PIVOT,EXTENSION,IDLE
    }
}
