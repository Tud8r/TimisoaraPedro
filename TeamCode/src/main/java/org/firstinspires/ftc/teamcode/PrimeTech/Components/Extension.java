package org.firstinspires.ftc.teamcode.PrimeTech.Components;

import static org.firstinspires.ftc.teamcode.PrimeTech.Global.Global.hardwareMap;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.PrimeTech.Global.Global;

public class Extension {
    private static Extension instance = null;
    private DcMotorEx leftMotor = null;
    private DcMotorEx rightMotor = null;

    private PIDController controller;
    public static double p = 0, i = 0.0, d = 0;
    public static double f = 0.0;

    public static synchronized Extension getInstance() {
        if (instance == null) {
            instance = new Extension();
        }
        return instance;
    }

    public void init(){
        controller = new PIDController(p,i,d);
        leftMotor = hardwareMap.get(DcMotorEx.class,"leftExtension");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightExtension");

        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//      rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void runToTarget(int target){
        controller.setPID(p,i,d);
        double pid = controller.calculate(target, leftMotor.getCurrentPosition());
        double ff = Math.sin(Pivot.getInstance().getAngle()) * f;
        double power = pid + ff;

        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }
}
