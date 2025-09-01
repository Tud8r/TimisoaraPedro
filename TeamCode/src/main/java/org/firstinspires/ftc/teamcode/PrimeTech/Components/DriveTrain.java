package org.firstinspires.ftc.teamcode.PrimeTech.Components;

import static org.firstinspires.ftc.teamcode.PrimeTech.Global.Global.gamepad1;
import static org.firstinspires.ftc.teamcode.PrimeTech.Global.Global.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DriveTrain {
    private static DriveTrain instance = null;
    public static synchronized DriveTrain getInstance() {
        if (instance == null) {
            instance = new DriveTrain();
        }
        return instance;
    }

    DcMotor leftBack = null;
    DcMotor leftFront = null;
    DcMotor rightBack = null;
    DcMotor rightFront=null;
    public void init() {
        leftBack = hardwareMap.get(DcMotor.class,"leftBack");
        leftFront = hardwareMap.get(DcMotor.class,"leftFront");
        rightBack = hardwareMap.get(DcMotor.class,"rightBack");
        rightFront = hardwareMap.get(DcMotor.class,"rightFront");

        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

    }


    public void loop() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        double max = Math.max(Math.abs(x)+Math.abs(y)+Math.abs(rx) ,1);

        leftFront.setPower((y+x+rx)/max);
        leftBack.setPower((y - x + rx) / max);
        rightFront.setPower((y - x - rx) / max);
        rightBack.setPower((y + x - rx) / max);

    }
}