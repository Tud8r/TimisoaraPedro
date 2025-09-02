package org.firstinspires.ftc.teamcode.PrimeTech.TeleOp;

import com.bylazar.panels.Panels;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.PrimeTech.Components.DriveTrain;
import org.firstinspires.ftc.teamcode.PrimeTech.Components.Pivot;
import org.firstinspires.ftc.teamcode.PrimeTech.Components.Tray;
import org.firstinspires.ftc.teamcode.PrimeTech.Global.Global;
import com.bylazar.graph.PanelsGraph;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "TeleOp")
public class MainTeleOp extends OpMode {

    private CRServo servo;
    private DcMotor motor;

    @Override
    public void init() {

//        servo = hardwareMap.get(CRServo.class,"Servo");
        motor = hardwareMap.get(DcMotor.class, "Motor");
//        servo.setDirection(DcMotorSimple.Direction.REVERSE);

        Global.hardwareMap = hardwareMap;
        Global.gamepad1 = gamepad1;

//        DriveTrain.getInstance().init();
//        Tray.getInstanc  ,me().init();
//        Pivot.getInstance().init();
    }

    @Override
    public void loop() {

        if(gamepad1.square){
            motor.setPower(1);
        }
        else{
            motor.setPower(0);
        }


//        DriveTrain.getInstance().loop();
//        Tray.getInstance().loop();
    }
}
