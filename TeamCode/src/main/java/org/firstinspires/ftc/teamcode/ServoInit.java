package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name= "servoinit")
public class ServoInit extends OpMode {
    private Servo servoLeft;
    private Servo servoRight;

    @Override
    public void init() {
        servoLeft = hardwareMap.get(Servo.class, "servoLeft");
        servoRight = hardwareMap.get(Servo.class, "servoRight");
    }

    @Override
    public void loop() {
        if(gamepad1.square){
            servoLeft.setPosition(1);
            servoRight.setPosition(1);
        }
        if(gamepad1.cross){
            servoLeft.setPosition(0.5);
            servoRight.setPosition(0.5);
        }
        if(gamepad1.circle){
            servoLeft.setPosition(0);
            servoRight.setPosition(0);
        }
        telemetry.addData("left",servoLeft.getPosition());
        telemetry.addData("right",servoRight.getPosition());
        telemetry.update();
    }
}
