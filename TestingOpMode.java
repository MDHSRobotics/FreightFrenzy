package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TestingOpMode (Blocks to Java)", group = "")
public class TestingOpMode extends LinearOpMode {

  private DcMotor motorTest;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    double tgtPower;

    motorTest = hardwareMap.dcMotor.get("motorTest");

    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Put loop blocks here.
        tgtPower = -gamepad1.left_stick_y;
        motorTest.setPower(tgtPower);
        telemetry.update();
      }
    }
  }
}
