package org.firstinspires.ftc.teamcode.PrimeTech.Components;

import android.widget.Switch;

import org.firstinspires.ftc.teamcode.PrimeTech.Gamepad.Gamepad;

public class AllModes {

    private final double INTAKE_PIVOT = 100;
    private final double OUTAKE_PIVOT = 2000;
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
            case EXTENSION:
                Extension.getInstance().setTarget(0);
                if(Extension.getInstance().isDone()){
                    retract = Retract.PIVOT;
                }
                break;
            case PIVOT:
                Pivot.getInstance().setTarget(0);
                if(Pivot.getInstance().isDone()){
                    retract = Retract.IDLE;
                }
                break;
            case IDLE:
                switch (state){
                    case INTAKE:
                        Pivot.getInstance().setTarget(INTAKE_PIVOT);
                        break;
                    case OUTAKE:
                        Pivot.getInstance().setTarget(OUTAKE_PIVOT);
                        break;
                    case IDLE:
                        Pivot.getInstance().setTarget(0);
                        Extension.getInstance().setTarget(0);
                        break;
                }
                break;
        }
    }

    public void Intake(){
        retract = Retract.EXTENSION;
        state = State.INTAKE;
    }

    public void Outake(){
        retract = Retract.EXTENSION;
        state = State.OUTAKE;
    }
    public void Idle(){

    }

    enum State{
        RESET,INTAKE,OUTAKE,IDLE
    }
    enum Retract{
        PIVOT,EXTENSION,IDLE,
    }
}
