package org.firstinspires.ftc.teamcode.PrimeTech.Components;

import static org.firstinspires.ftc.teamcode.PrimeTech.Global.Global.hardwareMap;
import static org.firstinspires.ftc.teamcode.PrimeTech.Global.Global.telemetry;

import com.arcrobotics.ftclib.controller.PIDController;
import com.bylazar.graph.GraphManager;
import com.bylazar.graph.PanelsGraph;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Configurable
public class Pivot {

    public static final double MAX_TICKS = 2100;
    public static final double MIN_TICKS = 0.0;

    public static final double INTAKE = 0;
    public static final double OUTAKE = 2100;

    private PIDController controller;
    public static double p = 0, i = 0.0, d = 0;
    public static double f = 0.0;

    public static final double ticks_in_degrees = (double) 8192 / 360;
    public static double target = 0;
    public static DcMotorEx motorPivot = null;
    private static Pivot instance = null;
//    private PIDController controller; ???

    public static double tolerance = 100;
    private TelemetryManager telemetryManager = null;
    private GraphManager graphManager = PanelsGraph.INSTANCE.getManager();


    public static synchronized Pivot getInstance() {
        if (instance == null) {
            instance = new Pivot();
        }
        return instance;
    }
    public void init(){
        controller = new PIDController(p,i,d);
        telemetryManager = PanelsTelemetry.INSTANCE.getTelemetry();
        motorPivot = hardwareMap.get(DcMotorEx.class, "motorPivot");

        motorPivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorPivot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorPivot.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    public void loop(){
        runToTarget();
    }
    public void setTarget(double pos){
        target = pos;
    }
    public void setIntake(){
        target = INTAKE;
    }
    public void setOutake(){

    }


    private void runToTarget(){
        controller.setPID(p,i,d);
        int armPos = motorPivot.getCurrentPosition();
        double pid = controller.calculate(armPos,target);
        double ff = Math.cos(getAngle()) * f;
        double power = (pid + ff);

        motorPivot.setPower(power);

        telemetryManager.addData("pos" , armPos);
        telemetryManager.addData("target" , target);
        telemetryManager.addData("isDone" , isDone());
        telemetryManager.update();

        graphManager.addData("pos" , armPos);
        graphManager.addData("target" , target);
        graphManager.update();

        telemetry.addData("pos",armPos);
        telemetry.addData("target",target);
        telemetry.update();
    }
    public double getAngle(){
        return Math.toRadians(motorPivot.getCurrentPosition()  / ticks_in_degrees);
    }
    public boolean isDone(){
        return (Math.abs(motorPivot.getCurrentPosition()-target)<tolerance);
    }


}
