package org.firstinspires.ftc.teamcode.PrimeTech.Components;

import static org.firstinspires.ftc.teamcode.PrimeTech.Global.Global.hardwareMap;
import static org.firstinspires.ftc.teamcode.PrimeTech.Global.Global.telemetry;

import com.arcrobotics.ftclib.controller.PIDController;
import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.graph.GraphManager;
import com.bylazar.graph.PanelsGraph;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.PrimeTech.Global.Global;

@Configurable
public class Extension {
    private static Extension instance = null;
    private DcMotorEx leftMotor = null;
    private DcMotorEx rightMotor = null;
    private double tolerance = 100;
    private PIDController controller;
    public static double p = 0, i = 0.0, d = 0;
    public static double f = 0.0;

    private static double target = 0;

    private TelemetryManager telemetryManager = null;
    private GraphManager graphManager = PanelsGraph.INSTANCE.getManager();

    public static synchronized Extension getInstance() {
        if (instance == null) {
            instance = new Extension();
        }
        return instance;
    }

    public void init(){
        controller = new PIDController(p,i,d);
        leftMotor  = hardwareMap.get(DcMotorEx.class,"leftExtension");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightExtension");

        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//      rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetryManager = PanelsTelemetry.INSTANCE.getTelemetry();

    }

    public void loop(){
        if (rightMotor == null || leftMotor == null) {
            telemetryManager.addData("ERROR", "Motors not initialized!");
            telemetryManager.update();
            return;
        }
//        rightMotor.setPower(0.1);
//        leftMotor.setPower(0.1);
        runToTarget(target);
    }
    public void setTarget(double pos){
        target = pos;
    }
    public void runToTarget(double target){
        controller.setPID(p,i,d);

        double armPos = rightMotor.getCurrentPosition();
        double pid = controller.calculate(armPos, target);

//        double ff = Math.sin(Pivot.getInstance().getAngle()) * f;
//        double power = pid + ff;
        double power  = pid ;
        leftMotor.setPower(power);
        rightMotor.setPower(power);

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
    public boolean isDone(){
        return (Math.abs(rightMotor.getCurrentPosition()-target)<tolerance);
    }
}
