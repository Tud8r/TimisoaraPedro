package org.firstinspires.ftc.teamcode.PrimeTech.Components;

import static org.firstinspires.ftc.teamcode.PrimeTech.Global.Global.gamepad1;
import static org.firstinspires.ftc.teamcode.PrimeTech.Global.Global.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Tray {
    private static Tray instance = null;
    public static synchronized Tray getInstance() {
        if (instance == null) {
            instance = new Tray();
        }
        return instance;
    }
    private DcMotor motor;
    public void init(){
        motor = hardwareMap.get(DcMotor.class,"trayMotor");
    }
    public void loop(){
        if (gamepad1.square) {
            motor.setPower(0.1);
        }
    }
}
