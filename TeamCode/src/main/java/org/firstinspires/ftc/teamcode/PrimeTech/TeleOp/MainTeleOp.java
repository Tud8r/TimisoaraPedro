package org.firstinspires.ftc.teamcode.PrimeTech.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.PrimeTech.Components.DriveTrain;
import org.firstinspires.ftc.teamcode.PrimeTech.Components.Pivot;
import org.firstinspires.ftc.teamcode.PrimeTech.Components.Tray;
import org.firstinspires.ftc.teamcode.PrimeTech.Global.Global;

@TeleOp(name = "TeleOp")
public class MainTeleOp extends OpMode {


    @Override
    public void init() {
        Global.hardwareMap = hardwareMap;
        Global.gamepad1 = gamepad1;

        DriveTrain.getInstance().init();
        Tray.getInstance().init();
        Pivot.getInstance().init();
    }

    @Override
    public void loop() {
        DriveTrain.getInstance().loop();
        Tray.getInstance().loop();
    }
}
