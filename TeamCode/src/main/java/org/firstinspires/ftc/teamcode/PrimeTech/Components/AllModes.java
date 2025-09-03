package org.firstinspires.ftc.teamcode.PrimeTech.Components;

import android.widget.Switch;

import org.firstinspires.ftc.teamcode.PrimeTech.Gamepad.Gamepad;

public class AllModes {

    private static AllModes instance;
    private State state;
    private Retract retract;
    public static synchronized AllModes getInstance() {
        if (instance == null) {
            instance = new AllModes();
        }
        return instance;
    }
    public void loop(){
        switch (retract){
            case IDLE:
                if(Gamepad.getInstance().dpad_down()) {
                    components = Modes.Components.EXTENSION;
                }
            case EXTENSION:
                Extension.getInstance().setTarget(0);
                if(Extension.getInstance().isDone()){
                    components= Modes.Components.PIVOT;
                }
                break;
            case PIVOT:
                Pivot.getInstance().setTarget(0);
                if(Pivot.getInstance().isDone()){
                    components= Modes.Components.IDLE;
                }
                break;
        }
            switch (state){

            }
        }
    }
    enum State{
        RESET,INTAKE,OUTAKE
    }
    enum Retract{
        PIVOT,EXTENSION,IDLE,
    }
}
