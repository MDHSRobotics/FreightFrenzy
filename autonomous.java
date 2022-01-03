package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Autonomous", group = "")
public class Autonomous extends LinearOpMode {
  private DcMotor FR;
  private DcMotor FL;
  private DcMotor BL;
  private DcMotor BR;
  
  private Servo duckie;
  
  private DistanceSensor cdv3_DistanceSensor;
  private ColorSensor cdv3;
  
  @Override
  public void runOpMode() {
    cdv3_DistanceSensor = hardwareMap.get(DistanceSensor.class, "cdv3");
    cdv3 = hardwareMap.get(ColorSensor.class, "cdv3");
    FR = hardwareMap.get(DcMotor.class, "FR");
    FL = hardwareMap.get(DcMotor.class, "FL");
    BL = hardwareMap.get(DcMotor.class, "BL");
    BR = hardwareMap.get(DcMotor.class, "BR");
    duckie = hardwareMap.get(Servo.class, "duckie");
    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      ducks();
      while (opModeIsActive()) {
        
        telemetry.addData("Distance Inch", cdv3_DistanceSensor.getDistance(DistanceUnit.CM));
        telemetry.addData("red", cdv3.red());
        telemetry.update();
      }
    }
  }
  private void forward() {
    BL.setPower(1);
    BR.setPower(-1);
    FL.setPower(1);
    FR.setPower(-1);
  }
  private void back() {
    BL.setPower(-1);
    BR.setPower(1);
    FL.setPower(-1);
    FR.setPower(1);
  }
  private void left() {
    BL.setPower(-1);
    BR.setPower(-1);
    FL.setPower(-1);
    FR.setPower(-1);
  }
  private void right() {
    BL.setPower(1);
    BR.setPower(1);
    FL.setPower(1);
    FR.setPower(1);
  }
  private void strafeleft() {
    BL.setPower(-1);
    BR.setPower(1);
    FL.setPower(1);
    FR.setPower(-1);
  }
  private void straferight() {
    BL.setPower(1);
    BR.setPower(-1);
    FL.setPower(-1);
    FR.setPower(1);
  }
  private void stopmove() {
    BL.setPower(0);
    BR.setPower(0);
    FL.setPower(0);
    FR.setPower(0);
  }
  private void ducks() {
    duckie.setPosition(1);
    sleep(3000);
    duckie.setPosition(0.5);
  }
  
}