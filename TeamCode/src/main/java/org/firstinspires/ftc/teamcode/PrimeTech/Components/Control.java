package org.firstinspires.ftc.teamcode.PrimeTech.Components;

import org.firstinspires.ftc.teamcode.PrimeTech.Gamepad.Gamepad;

public class Control {
    private static Control instance;

    public static synchronized Control getInstance(){
        if(instance == null){
            instance = new Control();
        }
        return instance;
    }

    public void loop(){
        if(Gamepad.getInstance().dpad_up()){
            AllModes.getInstance().Outake();
        }
        if(Gamepad.getInstance().dpad_down()){
            AllModes.getInstance().Intake();
        }
        if(Gamepad.getInstance().dpad_left()){
            AllModes.getInstance().Idle();
        }
    }
}
