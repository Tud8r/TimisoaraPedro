package org.firstinspires.ftc.teamcode.PrimeTech.Components;

import static org.firstinspires.ftc.teamcode.PrimeTech.Global.Global.gamepad1;
import static org.firstinspires.ftc.teamcode.PrimeTech.Global.Global.hardwareMap;
import static org.firstinspires.ftc.teamcode.PrimeTech.Global.Global.telemetry;

import android.content.pm.LauncherApps;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.PrimeTech.Gamepad.Gamepad;

@Configurable
public class Claw {
    private static Claw instance;
    private Servo rotationServo;
    private Servo openingServo;
    public double pos=0.5;
    public double openPos=1;


    public final double OPENED = 0.9;
    public final double CLOSED = 0.55;

    public final double MID = 0.5;

    public TelemetryManager tele = PanelsTelemetry.INSTANCE.getTelemetry();

    public static synchronized Claw getInstance() {
        if (instance == null) {
            instance = new Claw();
        }
        return instance;
    }

    public void init(){
        rotationServo = hardwareMap.get(Servo.class, "rotationServo");
        openingServo = hardwareMap.get(Servo.class,"openingServo");
        openingServo.setPosition(OPENED);
        rotationServo.setPosition(MID);

    }
    public void loop(){


        if(Gamepad.getInstance().circle()){
            pos=pos + 0.05;
        }
        if(Gamepad.getInstance().square())
        {
            pos=pos-0.05;
        }
        if(Gamepad.getInstance().triangle()){
            open();
        }
        if(Gamepad.getInstance().cross()){
            close();
        }
        if(Gamepad.getInstance().dpad_up()){
            center();
        }



        telemetry.addData("pos",rotationServo.getPosition());
        telemetry.addData("openpos",openingServo.getPosition());
        telemetry.update();

    }
    public void open(){
        openingServo.setPosition(OPENED);
    }
    public void close(){
        openingServo.setPosition(CLOSED);
    }
    public void center(){
        rotationServo.setPosition(MID);
    }
}
