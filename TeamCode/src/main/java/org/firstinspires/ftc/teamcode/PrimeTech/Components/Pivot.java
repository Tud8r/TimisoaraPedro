package org.firstinspires.ftc.teamcode.PrimeTech.Components;

import static org.firstinspires.ftc.teamcode.PrimeTech.Global.Global.hardwareMap;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;


public class Pivot {
    private PIDController controller;
    public static double p = 0, i = 0.0, d = 0;
    public static double f = 0.0;

    public static final double ticks_in_degrees = (double) 8192 / 360;
    public static double target = 0;
    public static DcMotorEx motorPivot = null;
    private static Pivot instance = null;

//    private PIDController controller; ???


    public static synchronized Pivot getInstance() {
        if (instance == null) {
            instance = new Pivot();
        }
        return instance;
    }
    public void init(){
        controller = new PIDController(p,i,d);

        motorPivot = hardwareMap.get(DcMotorEx.class, "motorPivot");
    }
    public void setPosition(){
        controller.setPID(p,i,d);
        int armPos = motorPivot.getCurrentPosition();
        double pid = controller.calculate(armPos,target);
        double ff = Math.cos(Math.toRadians(target  /ticks_in_degrees)) * f;
        double power = pid + ff;

        motorPivot.setPower(power);
    }

}
