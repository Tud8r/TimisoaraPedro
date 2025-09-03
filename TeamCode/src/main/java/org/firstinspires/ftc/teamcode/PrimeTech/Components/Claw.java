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
    private Servo servoLeft;
    private Servo servoRight;
    public double pos=0.5;
    public double openPos=1;


    public final double PARALLEL = 0.5;
    public final double DOWN = 1;
    public final double UP = 0;
    public final double OPENED = 0.9;
    public final double CLOSED = 0.55;

    public final double MID = 0.5;

    boolean open = true;
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
        servoLeft = hardwareMap.get(Servo.class, "servoLeft");
        servoRight = hardwareMap.get(Servo.class, "servoRight");
        openingServo.setPosition(OPENED);
        rotationServo.setPosition(MID);
        servoLeft.setPosition(DOWN);
        servoRight.setPosition(DOWN);


    }
    public void loop(){


//        if(Gamepad.getInstance().circle()){
//            pos=pos + 0.05;
//        }
//        if(Gamepad.getInstance().square())
//        {
//            pos=pos-0.05;
//        }

        if(Gamepad.getInstance().cross()){
            setOpen();
        }



//        telemetry.addData("pos",rotationServo.getPosition());
//        telemetry.addData("openpos",openingServo.getPosition());
//        telemetry.update();

    }
    public void setOpen(){
        if(open){
            close();
        }
        else{
            open();
        }
        open = !open;


    }
    public void setUp(){
        servoLeft.setPosition(UP);
        servoRight.setPosition(UP);
    }
    public void setDown(){
        servoLeft.setPosition(DOWN);
        servoRight.setPosition(DOWN);
    }
    public void setForward(){
        servoLeft.setPosition(MID);
        servoRight.setPosition(MID);
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
